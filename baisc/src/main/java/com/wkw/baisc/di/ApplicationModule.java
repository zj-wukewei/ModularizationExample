package com.wkw.baisc.di;

import com.wkw.baisc.executor.PostExecutionThread;
import com.wkw.baisc.executor.ThreadExecutor;
import com.wkw.baisc.executor.job.JobExecutor;
import com.wkw.baisc.executor.job.UIThread;
import com.wkw.baisc.network.MrService;

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
    MrService provideZhilanService() {
        return new MrService();
    }
}
