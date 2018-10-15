package com.wkw.modularization;


import android.app.Activity;

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
import okhttp3.HttpUrl;
import timber.log.Timber;

/**
 * Created by wukewei on 2017/8/27.
 */

public class MrApplication extends BaseApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initInjector() {
        AppConfigModule.Builder builder = AppConfigModule.builder();
        builder.baseUrl(HttpUrl.parse("http://192.168.8.164:1001/"))
                .interceptorList(new ArrayList<>())
                .responseErrorListener(new ResponseListenerImpl())
                .imageLoaderStrategy(new GlideImageLoaderStrategy());
        DaggerAppComponent.builder()
                .application(this)
                .appConfigModule(builder.build())
                .build().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }


}
