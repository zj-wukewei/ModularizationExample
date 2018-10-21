package com.wkw.modularization;


import android.app.Activity;
import android.content.ContentProvider;

import com.wkw.commonbusiness.BaseApplication;
import com.wkw.uiframework.di.AppConfigModule;
import com.wkw.commonbusiness.exception.ResponseListenerImpl;
import com.wkw.imageloader.glide.GlideImageLoaderStrategy;
import com.wkw.modularization.di.DaggerAppComponent;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasContentProviderInjector;
import okhttp3.HttpUrl;
import timber.log.Timber;

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
