package com.vongihealth.network;

import android.app.Application;
import android.content.Context;

import com.vongihealth.network.retrofit.MrService;

import java.util.HashSet;

import okhttp3.Interceptor;

/**
 * Created by GoGo on 2018-4-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public final class NetWorkManager {

    public static Context mContext;

    public static boolean isDebug = false;
    public static String baseUrl;

    public static HashSet<Interceptor> mInterceptors = new HashSet<>();

    public static void init(String url, Application context) {
        mContext = context;
        baseUrl = url;
    }


    public static void addInterceptors(Interceptor interceptor) {
        mInterceptors.add(interceptor);
    }

    public static void isDebug(boolean debug) {
        isDebug = debug;
    }

    public static void setSSLFileNameAndPassword(String fileName, String password) {
        MrService.S_BKS_FILE_NAME = fileName;
        MrService.S_PASSWORD = password;
    }

}
