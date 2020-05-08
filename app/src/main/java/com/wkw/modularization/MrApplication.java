package com.wkw.modularization;


import android.content.ContentProvider;

import com.wkw.commonbusiness.BaseApplication;
import com.wkw.modularization.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

/**
 * Created by wukewei on 2017/8/27.
 */

public class MrApplication extends BaseApplication implements HasAndroidInjector {

    @Inject
    volatile DispatchingAndroidInjector<Object> androidInjector;

    private volatile boolean needToInject = true;


    @Inject
    DispatchingAndroidInjector<ContentProvider> dispatchingContentProviderInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
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
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}
