package com.wkw.uiframework.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.vongihealth.network.executor.PostExecutionThread;
import com.vongihealth.network.executor.ThreadExecutor;
import com.vongihealth.network.executor.job.JobExecutor;
import com.vongihealth.network.executor.job.UIThread;
import com.vongihealth.network.handler.ResponseErrorListener;
import com.vongihealth.network.handler.RxErrorHandler;
import com.vongihealth.network.retrofit.MrService;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

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
    ThreadExecutor provideThreadExecutor() {
        return new JobExecutor();
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }

    @Provides
    @Singleton
    RxErrorHandler provideRxErrorHander(Context context, ResponseErrorListener listener) {
        return RxErrorHandler.builder()
                .with(context)
                .responseErrorListener(listener)
                .build();
    }

    @Provides
    @Singleton
    MrService provideMrService(HttpUrl url, OkHttpClient okHttpClient) {
        return new MrService(url, okHttpClient);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    @Provides
    @Singleton
    RxSharedPreferences provideRxSharedPreferences(SharedPreferences sharedPreferences) {
        return RxSharedPreferences.create(sharedPreferences);
    }

//    @Provides
//    @Singleton
//    ViewActionQueueProvider provideViewActionQueueProvider(RxErrorHandler rxErrorHandler) {
//        return new ViewActionQueueProviderImpl(rxErrorHandler);
//    }
}
