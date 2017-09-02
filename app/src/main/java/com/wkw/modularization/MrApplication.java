package com.wkw.modularization;

import android.app.Activity;
import android.app.Application;

import com.wkw.modularization.di.DaggerAppComponent;
import com.wkw.sdk.Ext;
import com.wkw.sdk.utils.ConfigManager;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by wukewei on 2017/8/27.
 */

public class MrApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initExtension();
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
        ConfigManager.init(this);
    }

    private void initExtension() {
        Ext.init(this, new ExtImpl());
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    static class ExtImpl extends Ext {

        @Override
        public String getCurOpenId() {
            return null;
        }

        @Override
        public int getScreenHeight() {
            return 0;
        }

        @Override
        public int getScreenWidth() {
            return 0;
        }

        @Override
        public boolean isAvailable() {
            return false;
        }

        @Override
        public boolean isDebuggable() {
            return false;
        }
    }

}
