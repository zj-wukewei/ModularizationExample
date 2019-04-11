package com.wkw.uiframework.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.Iterator;
import java.util.ServiceLoader;

public class BaseApplication extends Application {

    private AppDelegate mAppDelegate;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (mAppDelegate == null) {
            mAppDelegate = new AppDelegate();
        }
        mAppDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppDelegate.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mAppDelegate.onTerminate();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mAppDelegate.onLowMemory();
    }

}
