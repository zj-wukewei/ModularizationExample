package com.wkw.uiframework.base.mvp;


import androidx.lifecycle.Lifecycle;

import com.vongihealth.network.handler.RxErrorHandler;
import com.wkw.ext.utils.guava.Preconditions;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Created by GoGo on 2018-4-11.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> viewRef;

    @Inject
    RxErrorHandler mRxErrorHandler;

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
    }


    @Override
    public V getView() {
        return isViewAttached() ? viewRef.get() : null;
    }

    protected final RxErrorHandler getRxErrorHandler() {
        Preconditions.checkNotNull(mRxErrorHandler, "请全局提供RxErrorHandler");
        return mRxErrorHandler;
    }

    protected Lifecycle getLifecycle() {
        if (getView() != null) {
            return getView().getLifecycle();
        }
        return null;
    }

    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }


}
