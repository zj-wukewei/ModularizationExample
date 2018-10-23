package com.wkw.commonbusiness.network;

import android.content.Context;
import android.os.Build;

import com.wkw.commonbusiness.UserContentObserver;
import com.wkw.commonbusiness.utils.LoginModuleUtils;
import com.wkw.ext.Ext;
import com.wkw.ext.utils.StringUtils;

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
    public static final String DEVICE_MODEL = "DEVICE-MODEL";
    public static final String DEVICE_SYSTEM = "DEVICE-SYSTEM";
    public static final String HEADER_TOKEN = "TOKEN";

    private String token = "";

    public HeadInterceptor(Context context) {
        LoginModuleUtils.registerUserContentObserver(context, new UserContentObserver(context, entity -> {
            if (entity != null && !StringUtils.isEmpty(entity.getToken()) && !entity.getToken().equals(token)) {
                token = entity.getToken();
            }
        }));
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .method(original.method(), original.body())
                .addHeader(HEADER_APP_ID, HEADER_APP_ID_VALUE)
                .addHeader(HEADER_APP_MODEl, Build.MODEL + Build.VERSION.RELEASE)
                .addHeader(HEADER_APP_VER, String.valueOf(Ext.g().getVersionCode()))
                .addHeader(DEVICE_SYSTEM, Build.VERSION.RELEASE)
                .addHeader(DEVICE_MODEL, Build.MODEL)
                .addHeader(HEADER_TOKEN, token)
                .header(ACCEPT_LANGUAGE, Locale.getDefault().getLanguage())
                .build();
//        Logger.d(TAG, String.format("Sending request %s", toGetUrl(request)));
        return chain.proceed(request);
    }
}
