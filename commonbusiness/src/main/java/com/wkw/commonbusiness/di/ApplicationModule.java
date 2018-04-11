package com.wkw.commonbusiness.di;

import android.app.Application;
import android.content.Context;

import com.vongihealth.network.executor.PostExecutionThread;
import com.vongihealth.network.executor.ThreadExecutor;
import com.vongihealth.network.executor.job.JobExecutor;
import com.vongihealth.network.executor.job.UIThread;
import com.vongihealth.network.handler.RxErrorHandler;
import com.vongihealth.network.retrofit.MrService;
import com.wkw.basic.cache.UserCache;
import com.wkw.basic.cache.UserCacheImpl;
import com.wkw.commonbusiness.entity.UserSystem;
import com.wkw.commonbusiness.exception.ResponseLisenterImpl;

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
    RxErrorHandler provideRxErrorHandler(Context context) {
        return RxErrorHandler.builder().with(context).responseErrorListener(new ResponseLisenterImpl()).build();
    }

    @Provides
    @Singleton
    MrService provideMrService() {
        return  MrService.getInstance();
    }

    @Provides
    @Singleton
    UserCache provideUserCache(UserCacheImpl userCache) {
        return userCache;
    }
}
