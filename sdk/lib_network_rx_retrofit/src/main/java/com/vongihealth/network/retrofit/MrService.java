package com.vongihealth.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GoGo on 2018-4-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public class MrService {
    private static final String TAG = "MrService";
    private Retrofit mRetrofit;

    public <T> T createApi(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    public MrService(HttpUrl httpUrl, OkHttpClient okHttpClient) {
        final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Retrofit.Builder builder = new Retrofit
                .Builder()
                .baseUrl(httpUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient);
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        mRetrofit = builder.build();
    }

}
