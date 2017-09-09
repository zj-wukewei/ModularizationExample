package com.wkw.commonbusiness.di;

import android.app.Application;
import android.content.Context;

import com.wkw.basic.cache.UserCache;
import com.wkw.basic.cache.UserCacheImpl;
import com.wkw.basic.executor.PostExecutionThread;
import com.wkw.basic.executor.ThreadExecutor;
import com.wkw.basic.executor.job.JobExecutor;
import com.wkw.basic.executor.job.UIThread;
import com.wkw.basic.network.MrService;
import com.wkw.commonbusiness.entity.UserSystem;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wukewei on 2017/8/25.
 */

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    UserSystem provideUserSystem() {
        return new UserSystem();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    MrService provideMrService() {
        return new MrService();
    }

    @Provides
    @Singleton
    UserCache provideUserCache(UserCacheImpl userCache) {
        return userCache;
    }
}
