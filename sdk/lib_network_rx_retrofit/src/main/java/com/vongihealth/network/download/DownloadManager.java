package com.vongihealth.network.download;

import android.text.TextUtils;

import com.vongihealth.network.retrofit.MrService;

import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import timber.log.Timber;

/**
 * @author GoGo on 2019-01-29.
 */
public class DownloadManager {

    private static final String IDENTIFICATION_HEADER = "vongihealth";

    private final Interceptor mInterceptor;
    private DownApiService mDownApiService = null;
    private Map<String, ProgressCallBack> mResponseListeners = new WeakHashMap<>();


    public DownloadManager() {
        this.mInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Timber.d("DownloadManager intercept");
                return wrapResponseBody(chain.proceed(chain.request()));
            }
        };
    }

    public Interceptor getInterceptor() {
        return mInterceptor;
    }

    public void setMrService(MrService mrService) {
        if (mDownApiService == null) {
            this.mDownApiService = mrService.createApi(DownApiService.class);
        }
    }

    private Response wrapResponseBody(Response response) {
        if (response == null)
            return response;

        String key = response.request().url().toString();
        if (!TextUtils.isEmpty(response.request().header(IDENTIFICATION_HEADER))) { //从 header 中拿出有标识符的 url
            key = response.request().header(IDENTIFICATION_HEADER);
        }


        if (response.body() == null)
            return response;

        if (mResponseListeners.containsKey(key)) {
            ProgressCallBack callBack = mResponseListeners.get(key);
            return response.newBuilder()
                    .body(new ProgressResponseBody(response.body(), callBack))
                    .build();
        }
        return response;
    }


    public Disposable downLoadUrl(String downUrl, final ProgressCallBack callBack) {
        checkNotNull(mDownApiService, "mDownApiService can not null");
        addResponseListener(downUrl, callBack);
        return mDownApiService.download(downUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(callBack::saveFile)
                .observeOn(AndroidSchedulers.mainThread()) //在主线程中更新ui
                .subscribeWith(new DownLoadSubscriber(callBack));
    }


    private void addResponseListener(String url, ProgressCallBack listener) {
        checkNotNull(url, "url cannot be null");
        checkNotNull(listener, "listener cannot be null");
        ProgressCallBack progressListener;
        synchronized (DownloadManager.class) {
            progressListener = mResponseListeners.get(url);
            if (progressListener == null) {
                mResponseListeners.put(url, listener);
            }
        }
    }


    private interface  DownApiService {
        @Streaming
        @GET
        Observable<ResponseBody> download(@Url String url);
    }


    static <T> T checkNotNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
}
