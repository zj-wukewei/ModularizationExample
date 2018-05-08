package com.wkw.knowledge.debug;

import android.app.Application;

import com.wkw.uiframework.di.ApplicationModule;
import com.wkw.knowledge.di.KnowledgeActivityModule;
import com.wkw.knowledge.di.KnowledgeDataRepositoryModule;

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
        KnowledgeDataRepositoryModule.class, KnowledgeActivityModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }
    void inject(KnowledgeApplication mrApplication);
}
