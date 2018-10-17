package com.wkw.uiframework.app;

import android.content.Context;

public interface IApplicationLifecycle {
    void attachBaseContext(Context base);
    void onCreate();
    void onTerminate();
    void onLowMemory();
}
