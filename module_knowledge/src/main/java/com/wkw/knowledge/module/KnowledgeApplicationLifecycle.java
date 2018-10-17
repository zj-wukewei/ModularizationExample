package com.wkw.knowledge.module;

import android.content.Context;
import android.util.Log;

import com.google.auto.service.AutoService;
import com.wkw.uiframework.app.IApplicationLifecycle;

import timber.log.Timber;

@AutoService(IApplicationLifecycle.class)
public class KnowledgeApplicationLifecycle implements IApplicationLifecycle {
    @Override
    public void attachBaseContext(Context base) {

    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onLowMemory() {

    }
}
