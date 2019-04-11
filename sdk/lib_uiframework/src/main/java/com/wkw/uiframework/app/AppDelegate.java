package com.wkw.uiframework.app;

import android.content.Context;

import java.util.ServiceLoader;

/**
 * @author GoGo on 2019-04-07.
 */
public class AppDelegate implements IApplicationLifecycle {

    private ServiceLoader<IApplicationLifecycle> mIApplicationLifecycle;

    public AppDelegate() {
        this.mIApplicationLifecycle = ServiceLoader.load(IApplicationLifecycle.class);
    }

    @Override
    public void attachBaseContext(Context base) {
        if (mIApplicationLifecycle != null) {
            for (IApplicationLifecycle iApplicationLifecycle : mIApplicationLifecycle) {
                iApplicationLifecycle.attachBaseContext(base);
            }
        }
    }

    @Override
    public void onCreate() {
        if (mIApplicationLifecycle != null) {
            for (IApplicationLifecycle iApplicationLifecycle : mIApplicationLifecycle) {
                iApplicationLifecycle.onCreate();
            }
        }
    }

    @Override
    public void onTerminate() {
        if (mIApplicationLifecycle != null) {
            for (IApplicationLifecycle iApplicationLifecycle : mIApplicationLifecycle) {
                iApplicationLifecycle.onTerminate();
            }
        }
    }

    @Override
    public void onLowMemory() {
        if (mIApplicationLifecycle != null) {
            for (IApplicationLifecycle iApplicationLifecycle : mIApplicationLifecycle) {
                iApplicationLifecycle.onLowMemory();
            }
        }
    }
}
