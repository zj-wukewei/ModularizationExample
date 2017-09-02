package com.wkw.commonbusiness.mvp;

import java.lang.ref.WeakReference;

/**
 * Created by wukewei on 2017/8/28.
 */

public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> viewRef;

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
    public V getView() {
        return isViewAttached() ? viewRef.get() : null;
    }

    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }
}
