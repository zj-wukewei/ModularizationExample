package com.wkw.uiframework.di;


import com.vongihealth.network.handler.ResponseErrorListener;
import com.wkw.ext.Ext;
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

    private AppConfigModule(Builder builder) {
        baseUrl = builder.baseUrl;
        interceptorList = builder.interceptorList;
        sSLSocketFactory = builder.sSLSocketFactory;
        responseErrorListener = builder.responseErrorListener;
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
    public List<Interceptor> getInterceptorList() {
        return interceptorList;
    }

    @Provides
    @Singleton
    public OkHttpClient getOkHttpClient(List<Interceptor> interceptors) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        if (Ext.g().isDebuggable()) {
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

        for (Interceptor i : interceptors) {
            client.addInterceptor(i);
        }

        if (sSLSocketFactory != null) {
            client.sslSocketFactory(sSLSocketFactory);
        }
        return client.build();
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
