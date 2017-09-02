package com.wkw.basic.network;

import android.os.Build;

import com.wkw.sdk.Ext;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wukewei on 2017/8/25.
 */

public class HeadInterceptor implements Interceptor {
    private static final String TAG = "HeadInterceptor";
    public static final String HEADER_APP_ID = "APP-ID";
    // iOS: 1, Android: 2
    public static final String HEADER_APP_ID_VALUE = "2";
    public static final String HEADER_APP_MODEl = "APP-MODEL";

    public static final String ACCEPT_LANGUAGE = "ACCEPT-LANGUAGE";
    public static final String HEADER_APP_VER = "APP-VERSION";
    public static final String HEADER_TOKEN = "TOKEN";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .method(original.method(), original.body())
                .addHeader(HEADER_APP_ID, HEADER_APP_ID_VALUE)
                .addHeader(HEADER_APP_MODEl, Build.MODEL + Build.VERSION.RELEASE)
                .addHeader(HEADER_APP_VER, String.valueOf(Ext.g().getVersionCode()))
                .addHeader(HEADER_TOKEN, MrService.token)
                .header(ACCEPT_LANGUAGE, Locale.getDefault().getLanguage())
                .build();
//        Logger.d(TAG, String.format("Sending request %s", toGetUrl(request)));
        return chain.proceed(request);
    }
}
