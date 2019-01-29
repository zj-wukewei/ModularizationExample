package com.wkw.modularization;


import android.app.Activity;
import android.content.ContentProvider;

import com.squareup.leakcanary.LeakCanary;
import com.wkw.commonbusiness.BaseApplication;
import com.wkw.modularization.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasContentProviderInjector;

/**
 * Created by wukewei on 2017/8/27.
 */

public class MrApplication extends BaseApplication implements HasActivityInjector, HasContentProviderInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private volatile boolean needToInject = true;


    @Inject
    DispatchingAndroidInjector<ContentProvider> dispatchingContentProviderInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
        LeakCanary.install(this);
    }

    private void initInjector() {
        if (needToInject) {
            synchronized (this) {
                if (needToInject) {
                    DaggerAppComponent.builder()
                            .application(this)
                            .appConfigModule(providerAppConfigModule().build())
                            .build().inject(this);
                    setInjected();
                }
            }
        }
    }

    void setInjected() {
        needToInject = false;
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


    @Override
    public AndroidInjector<ContentProvider> contentProviderInjector() {
        initInjector();
        return dispatchingContentProviderInjector;
    }

}
