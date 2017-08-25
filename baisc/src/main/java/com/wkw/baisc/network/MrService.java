package com.wkw.baisc.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wkw.sdk.Ext;
import com.wkw.sdk.utils.ConfigManager;
import com.wkw.sdk.utils.Logger;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wukewei on 2017/8/25.
 */

public class MrService {
    private static final String TAG = "MrService";
    //private static final String API_DEV_URL = "http://192.168.3.19:8086/simo/";
    private static final String API_DEV_URL = "https://mu.simochina.com/simo/";
    //private static final String API_PRODUCT_URL = "http://192.168.3.19:8086/simo/";
    private static final String API_PRODUCT_URL = "https://mu.simochina.com/simo/";
    public static final boolean INITIAL_ENVIRONMENT_DEV = Ext.g().isDebuggable();
    public static String token = ConfigManager.getString("token", "", ConfigManager.KEY_ACCOUNT);
    private static MrService mInstance;
    private Retrofit mRetrofit;

//    public static ZhilanService getInstance() {
//        if (mInstance == null) {
//            synchronized (ZhilanService.class) {
//                if (mInstance == null) {
//                    mInstance = new ZhilanService();
//                }
//            }
//        }
//        return mInstance;
//    }

    public static String getBaseUrl() {
        return INITIAL_ENVIRONMENT_DEV ? API_DEV_URL : API_PRODUCT_URL;
    }

    public <T> T createApi(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    public MrService() {
        this(true);
    }

    private MrService(boolean useRxJava) {
        final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(getClient());
        if (useRxJava) {
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        mRetrofit = builder.build();
    }

    private OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (INITIAL_ENVIRONMENT_DEV) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        return new OkHttpClient.Builder().addInterceptor(new HeadInterceptor())
                .addInterceptor(logging)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .hostnameVerifier((hostname, session) -> true)
                //.sslSocketFactory(getSSLCertifcation())
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(HttpUrl.parse(url.host()), cookies);
                    }

                    @Override public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
                        return cookies != null ? cookies : new ArrayList<>();
                    }
                })
                .build();
    }

    private  SSLSocketFactory getSSLCertifcation() {
        SSLContext sslContext = null;
        try {
            KeyStore ksTrust = KeyStore.getInstance("BKS");
            InputStream is = Ext.getContext().getAssets().open("xxxx.bks");
            ksTrust.load(is, "xxxxx".toCharArray());

            // TrustManager decides which certificate authorities to use.
            TrustManagerFactory tmf =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ksTrust);
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
        } catch (Exception e) {
            Logger.e(TAG, "ssl初始化出错:  " + e.getMessage());
            e.printStackTrace();
        }
        return sslContext.getSocketFactory();
    }
}
