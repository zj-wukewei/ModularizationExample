package com.wkw.ext;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import timber.log.Timber;

/**
 * Created by wukewei on 2017/8/25.
 */

public abstract class Ext {
    private static final String TAG = "Ext";

    private static Context sContext = null;
    private static Application sApplication = null;

    private static String sPackageName;
    private static String sVersionName;
    private static String sBuildNumber;
    private static int sVersionCode = 0;

    private static Ext sInstance = null;

    public static Ext g() {
        if (sInstance == null) {
            throw new RuntimeException("Ext 没有初始化!");
        }
        return sInstance;
    }

    public static void init(Application app, Ext instance) {
        sContext = app.getApplicationContext();
        sApplication = app;
        sInstance = instance;
        sPackageName = sApplication.getPackageName();
        initVersionCodeAndName(sApplication);
    }

    private static void initVersionCodeAndName(Context context) {
        String version = "";
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(sPackageName, 0);
            sVersionCode = info.versionCode;
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Timber.e("initVersionCodeAndName");
        }
        sVersionName = version.substring(0, version.lastIndexOf('.'));
        sBuildNumber = version.substring(version.lastIndexOf('.') + 1, version.length());
    }

    public static Context getContext() {
        return sContext;
    }

    public static Application getApplication() {
        return sApplication;
    }

    public abstract String getCurOpenId();

    public String getPackageName() {
        return sPackageName;
    }

    public int getVersionCode() {
        return sVersionCode;
    }

    public String getVersionName() {
        return sVersionName;
    }

    public String getBuilderNumber() {
        return sBuildNumber;
    }

    public abstract int getScreenHeight();

    public abstract int getScreenWidth();

    // Network
    public abstract boolean isAvailable();

    public abstract boolean isDebuggable();

    //统一处理全局的退出app
    public abstract void handleLoginOut();
}
