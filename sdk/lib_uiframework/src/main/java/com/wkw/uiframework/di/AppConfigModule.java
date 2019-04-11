package com.wkw.uiframework.di;


import com.vongihealth.network.download.DownloadManager;
import com.vongihealth.network.handler.ResponseErrorListener;
import com.vongihealth.network.retrofit.MrService;
import com.wkw.ext.utils.guava.Preconditions;
import com.wkw.imageloader.ImageLoader;
import com.wkw.imageloader.ImageLoaderStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.SSLSocketFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by GoGo on 2018-5-2.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

@Module
public class AppConfigModule {
    private HttpUrl baseUrl;
    private List<Interceptor> interceptorList;
    private SSLSocketFactory sSLSocketFactory;
    private ResponseErrorListener responseErrorListener;
    private MrService mrService;
    private DownloadManager downloadManager;
    private Boolean isDebuggable;
    private OkHttpClient okHttpClient;

    private AppConfigModule(Builder builder) {
        baseUrl = builder.baseUrl;
        interceptorList = builder.interceptorList;
        sSLSocketFactory = builder.sSLSocketFactory;
        responseErrorListener = builder.responseErrorListener;
        isDebuggable = builder.isDebuggable;
        OkHttpClient.Builder okHttpClientBuilder = getOkHttpClient(builder.interceptorList);
        downloadManager = new DownloadManager(okHttpClientBuilder);
        okHttpClient = okHttpClientBuilder.build();
        mrService = new MrService(builder.baseUrl, okHttpClient);
        downloadManager.setMrService(mrService);
    }


    private OkHttpClient.Builder getOkHttpClient(List<Interceptor> interceptors) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        if (isDebuggable) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        OkHttpClient.Builder client = new OkHttpClient.Builder()
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

        for (Interceptor i : interceptors) {
            client.addInterceptor(i);
        }
        client.addInterceptor(logging);
        if (sSLSocketFactory != null) {
            client.sslSocketFactory(sSLSocketFactory);
        }
        return client;
    }

    @Provides
    @Singleton
    public HttpUrl getBaseUrl() {
        return baseUrl;
    }

    @Provides
    @Singleton
    public ResponseErrorListener getResponseErrorListener() {
        return responseErrorListener == null ? ResponseErrorListener.EMPTY : responseErrorListener;
    }


    @Provides
    @Singleton
    DownloadManager providerDownLoadManager() {
        return downloadManager;
    }

    @Provides
    @Singleton
    MrService provideMrService() {
        return mrService;
    }

    @Provides
    @Singleton
    public List<Interceptor> getInterceptorList() {
        return interceptorList;
    }

    @Provides
    @Singleton
    public OkHttpClient providerOkHttpClient() {
        return okHttpClient;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        //全局异常处理
        private ResponseErrorListener responseErrorListener;
        //网络相关配置
        private HttpUrl baseUrl;
        private List<Interceptor> interceptorList;
        private SSLSocketFactory sSLSocketFactory;
        private Boolean isDebuggable;

        private Builder() {
        }

        /***
         *  设置网络请求的地址
         * @param httpUrl 网络地址
         * @return
         */
        public Builder baseUrl(HttpUrl httpUrl) {
            baseUrl = httpUrl;
            return this;
        }


        /***
         * 设置okhttp请求的拦截器
         * @param val
         * @param
         * @return
         */
        public Builder interceptorList(List<Interceptor> val) {
            interceptorList = val;
            return this;
        }

        /***
         * 设置okhttp的证书
         * @param
         * @param
         * @return
         */
        public Builder sSLSocketFactory(SSLSocketFactory val) {
            sSLSocketFactory = val;
            return this;
        }

        /***
         * 全局错误处理
         * @param
         * @param
         * @return
         */
        public Builder responseErrorListener(ResponseErrorListener val) {
            responseErrorListener = val;
            return this;
        }


        /***
         * 是否debug
         * @param
         * @param
         * @return
         */
        public Builder isDebuggable(boolean debug) {
            isDebuggable = debug;
            return this;
        }

        /***
         * 图片加载策略模式
         * @param
         * @param
         * @return
         */
        public Builder imageLoaderStrategy(ImageLoaderStrategy imageLoaderStrategy) {
            Preconditions.checkNotNull(imageLoaderStrategy, "imageLoaderStrategy is not null");
            ImageLoader.getInstance().setImageLoaderStrategy(imageLoaderStrategy);
            return this;
        }

        public static Builder builder() {
            return new Builder();
        }

        public AppConfigModule build() {
            return new AppConfigModule(this);
        }
    }
}
