package com.wkw.modularization.di;

import android.app.Application;

import com.github.wkw.login.di.LoginModule;
import com.wkw.archives.di.ArchivesActivityModule;
import com.wkw.archives.di.ArchivesDataRepositoryModule;
import com.wkw.uiframework.di.AppConfigModule;
import com.wkw.uiframework.di.ApplicationModule;
import com.wkw.knowledge.di.KnowledgeActivityModule;
import com.wkw.knowledge.di.KnowledgeDataRepositoryModule;
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
        AndroidInjectionModule.class, ApplicationModule.class, AppConfigModule.class,
        ArchivesDataRepositoryModule.class, ArchivesActivityModule.class,
        KnowledgeDataRepositoryModule.class, KnowledgeActivityModule.class,
        LoginModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        Builder appConfigModule(AppConfigModule appConfigModule);

        AppComponent build();
    }

    void inject(MrApplication mrApplication);
}
