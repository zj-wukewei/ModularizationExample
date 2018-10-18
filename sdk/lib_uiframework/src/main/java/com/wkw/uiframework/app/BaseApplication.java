package com.wkw.uiframework.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.Iterator;
import java.util.ServiceLoader;

public class BaseApplication extends Application {

    private ServiceLoader<IApplicationLifecycle> mApplicationLiServiceLoader = null;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mApplicationLiServiceLoader = ServiceLoader.load(IApplicationLifecycle.class);
        for (IApplicationLifecycle iApplicationLifecycle : mApplicationLiServiceLoader) {
            iApplicationLifecycle.attachBaseContext(base);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        for (IApplicationLifecycle iApplicationLifecycle : mApplicationLiServiceLoader) {
            iApplicationLifecycle.onCreate();
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (IApplicationLifecycle iApplicationLifecycle : mApplicationLiServiceLoader) {
            iApplicationLifecycle.onTerminate();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (IApplicationLifecycle iApplicationLifecycle : mApplicationLiServiceLoader) {
            iApplicationLifecycle.onLowMemory();
        }
    }

}
