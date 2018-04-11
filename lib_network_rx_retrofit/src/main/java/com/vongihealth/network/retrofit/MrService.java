package com.vongihealth.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vongihealth.network.NetWorkManager;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GoGo on 2018-4-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Singleton
public class MrService {
    private static final String TAG = "MrService";
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    public static String S_BKS_FILE_NAME;
    public static String S_PASSWORD;


    public <T> T createApi(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    private static MrService mInstance;

    public static MrService getInstance() {
        if (mInstance == null) {
            synchronized (MrService.class) {
                if (mInstance == null) {
                    mInstance = new MrService();
                }
            }
        }
        return mInstance;
    }

    @Inject
    public MrService() {
        this(true);
    }


    public MrService(boolean useRxJava) {
        final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(NetWorkManager.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient());

        if (useRxJava) {
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        mRetrofit = builder.build();
    }

    private OkHttpClient getClient() {
        if (mOkHttpClient == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            if (NetWorkManager.isDebug) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            }

            OkHttpClient.Builder client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(12, TimeUnit.SECONDS)
                    .readTimeout(12, TimeUnit.SECONDS)
                    .cookieJar(new CookieJar() {
                        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                        @Override
                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                            cookieStore.put(HttpUrl.parse(url.host()), cookies);
                        }

                        @Override
                        public List<Cookie> loadForRequest(HttpUrl url) {
                            List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
                            return cookies != null ? cookies : new ArrayList<Cookie>();
                        }
                    });

            for (Interceptor i : NetWorkManager.mInterceptors) {
                client.addInterceptor(i);
            }

            SSLSocketFactory factory = getSSLCertifcation();
            if (factory != null) {
                client.sslSocketFactory(factory);
            }
            mOkHttpClient = client.build();
        }

        return mOkHttpClient;
    }

    private SSLSocketFactory getSSLCertifcation() {
        if (MrService.S_BKS_FILE_NAME == null || MrService.S_PASSWORD == null) {
            return null;
        }
        SSLContext sslContext = null;
        try {
            KeyStore ksTrust = KeyStore.getInstance("BKS");
            InputStream stream = NetWorkManager.mContext.getAssets().open(MrService.S_BKS_FILE_NAME);
            ksTrust.load(stream, MrService.S_PASSWORD.toCharArray());

            // TrustManager decides which certificate authorities to use.
            TrustManagerFactory tmf =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ksTrust);
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sslContext.getSocketFactory();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }
}
