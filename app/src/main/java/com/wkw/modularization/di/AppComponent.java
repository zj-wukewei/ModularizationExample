package com.wkw.modularization.di;

import android.app.Application;

import com.wkw.commonbusiness.di.ApplicationModule;
import com.wkw.modularization.MrApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by wukewei on 2017/8/28.
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class, ApplicationModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(MrApplication mrApplication);
}
