package com.vongihealth.network.repository;

import android.text.TextUtils;

import com.vongihealth.network.entity.MrResponse;
import com.vongihealth.network.exception.NetworkConnectionException;
import com.vongihealth.network.exception.ResponseException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * Created by GoGo on 2018-4-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class RepositoryUtils {
    private static final String TAG = "RepositoryUtils";
    private static final String DEFAULT_ERROR_MSG = "Unknown error";

    public static <T> ObservableTransformer<MrResponse<T>, T> handleResult() {
        return upstream -> upstream
                .flatMap((Function<MrResponse<T>, ObservableSource<T>>) tApiResponse -> {
                            if (tApiResponse == null) {
                                return Observable.error(new NetworkConnectionException());
                            } else if (tApiResponse.getStatusCode() != ResponseException.STATUS_CODE_SUCCESS) {
                                Timber.e(TAG, TextUtils.isEmpty(tApiResponse.getStatusMessage()) ? DEFAULT_ERROR_MSG : tApiResponse.getStatusMessage());
                                return Observable.error(new ResponseException(tApiResponse));
                            } else {
                                return createData(tApiResponse.getData());
                            }
                        }
                );
    }

    private static <T> Observable<T> createData(final T t) {
        return Observable.create(emitter -> {
            try {
                if (t != null) emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
