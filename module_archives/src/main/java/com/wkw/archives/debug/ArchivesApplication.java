package com.wkw.archives.debug;

import android.app.Activity;

import com.wkw.commonbusiness.BaseApplication;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by wukewei on 2017/9/9.
 */

public class ArchivesApplication extends BaseApplication implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
    }

    private void initInjector() {
        DaggerAppComponent.builder()
                .application(this)
                .build().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
