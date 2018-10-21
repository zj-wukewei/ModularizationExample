package com.github.wkw.login.debug;

import android.app.Activity;
import android.content.ContentProvider;

import com.wkw.commonbusiness.BaseApplication;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasContentProviderInjector;

/**
 * @author GoGo on 2018/10/21.
 */

public class LoginApplication extends BaseApplication implements HasActivityInjector, HasContentProviderInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<ContentProvider> dispatchingContentProviderInjector;
    private volatile boolean needToInject = true;


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
