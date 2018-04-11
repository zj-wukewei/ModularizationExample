package com.vongihealth.network.interactor;

import com.vongihealth.network.handler.RxErrorHandler;

/**
 * Created by GoGo on 2018-4-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class MrObserver<T> extends DefaultObserver<T> {

    private final RxErrorHandler mHandler;

    public MrObserver(RxErrorHandler handler) {
        mHandler = handler;
    }


    @Override
    public void onError(Throwable exception) {
        super.onError(exception);
        mHandler.getHandlerFactory().handleError(exception);
    }
}
