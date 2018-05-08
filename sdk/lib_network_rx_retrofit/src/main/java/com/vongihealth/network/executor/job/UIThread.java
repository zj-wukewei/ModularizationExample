package com.vongihealth.network.executor.job;


import com.vongihealth.network.executor.PostExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by hzjixiaohui on 2017-9-6.
 */

public class UIThread implements PostExecutionThread {
    public UIThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
