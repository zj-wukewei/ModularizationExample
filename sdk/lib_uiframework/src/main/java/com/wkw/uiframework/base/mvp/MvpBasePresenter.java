package com.wkw.uiframework.base.mvp;

import android.arch.lifecycle.LifecycleOwner;
import android.text.TextUtils;

import com.wkw.uiframework.base.mvp.action.ViewActionQueue;
import com.wkw.uiframework.base.mvp.action.ViewActionQueueProvider;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by GoGo on 2018-4-11.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> viewRef;
    private String viewId;
    protected ViewActionQueue<V> viewActionQueue;
    @Inject
    ViewActionQueueProvider viewActionQueueProvider;
    private final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<>(view);
        viewId = getIfViewNotNull();
        viewActionQueue = viewActionQueueProvider.queueFor(viewId);
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
        subscribeToViewActionQueue();
        viewActionQueue.resume();
    }

    private void subscribeToViewActionQueue() {
        disposables.add(viewActionQueue.viewActionsObservable()
                .subscribe(this::onViewAction, this::onViewActionQueueError));
    }

    private void onViewActionQueueError(final Throwable throwable) {
        logError(throwable);
        subscribeToViewActionQueue();
    }

    private void logError(final Throwable throwable) {
        if (!TextUtils.isEmpty(throwable.getMessage())) {
            Timber.d(throwable);
        }
    }

    @Override
    public void pause() {
        viewActionQueue.pause();
        disposables.clear();
    }

    @Override
    public void destroy() {
        viewActionQueue.destroy();
        viewActionQueueProvider.dispose(viewId);
        viewActionQueue = null;
        viewActionQueueProvider = null;
    }

    private void onViewAction(final Consumer<V> vAction) throws Exception {
        doIfViewNotNull(vAction);
    }

    private void doIfViewNotNull(final Consumer<V> whenViewNotNull) throws Exception {
        final V view = getView();
        if (view != null) {
            whenViewNotNull.accept(view);
        }
    }

    private String getIfViewNotNull() {
        final V v = this.getView();
        if (v != null && v.getViewId() != null) {
            return v.getViewId();
        }
        return "";
    }

    protected LifecycleOwner getLifecycleOwner() {
        if (getView() != null) {
            return getView().getLifecycleOwner();
        }
        return null;
    }

    @Override
    public V getView() {
        return isViewAttached() ? viewRef.get() : null;
    }

    protected boolean isViewAttached() {
        return viewRef != null && viewRef.get() != null;
    }


}
