package com.wkw.archives.debug;

import android.app.Application;

import com.wkw.archives.di.ArchivesActivityModule;
import com.wkw.archives.di.ArchivesDataRepositoryModule;
import com.wkw.commonbusiness.di.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by wukewei on 2017/8/28.
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class, ApplicationModule.class,
        ArchivesDataRepositoryModule.class, ArchivesActivityModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(ArchivesApplication mrApplication);
}
