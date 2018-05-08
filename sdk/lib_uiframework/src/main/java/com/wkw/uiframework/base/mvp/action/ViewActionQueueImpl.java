package com.wkw.uiframework.base.mvp.action;

import com.vongihealth.network.handler.RxErrorHandler;

import java.util.Iterator;
import java.util.LinkedList;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by GoGo on 2018-4-25.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class ViewActionQueueImpl<V> implements ViewActionQueue<V> {

    private final LinkedList<Consumer<V>> viewActions = new LinkedList<>();
    private final Object queueLock = new Object();

    private final PublishSubject<Consumer<V>> viewActionSubject = PublishSubject.create();
    private final CompositeDisposable disposables = new CompositeDisposable();
    private boolean isPaused = true;
    private final RxErrorHandler mRxErrorHandler;

    public ViewActionQueueImpl(RxErrorHandler rxErrorHandler) {
        this.mRxErrorHandler = rxErrorHandler;
    }

    @Override
    public void subscribeTo(Observable<Consumer<V>> observable, Consumer<V> onCompleteAction, Consumer<Throwable> errorAction) {
        disposables.add(observable.subscribe(this::onResult, throwable -> onError(throwable, errorAction), () -> onResult(onCompleteAction)));
    }

    @Override
    public void subscribeTo(Observable<Consumer<V>> observable, Consumer<Throwable> errorAction) {
        disposables.add(observable.subscribe(this::onResult, throwable -> onError(throwable, errorAction)));
    }

    @Override
    public void subscribeTo(Observable<Consumer<V>> observable) {
        disposables.add(observable.subscribe(this::onResult, throwable -> onError(throwable, null)));
    }

    @Override
    public void subscribeTo(Single<Consumer<V>> single, Consumer<Throwable> errorAction) {
        disposables.add(single.subscribe(this::onResult, throwable -> onError(throwable, errorAction)));
    }

    @Override
    public void subscribeTo(Single<Consumer<V>> single) {
        disposables.add(single.subscribe(this::onResult, throwable -> onError(throwable, null)));
    }

    @Override
    public void subscribeTo(Completable completable, Consumer<V> onCompleteAction, Consumer<Throwable> errorAction) {
        disposables.add(completable.subscribe(() -> onResult(onCompleteAction), throwable -> onError(throwable, errorAction)));
    }

    @Override
    public void pause() {
        isPaused = true;
    }

    private void onError(final Throwable throwable, final Consumer<Throwable> errorAction) throws Exception {
        mRxErrorHandler.getHandlerFactory().handleError(throwable);
        if (errorAction != null) {
            errorAction.accept(throwable);
        }
    }

    private void onResult(final Consumer<V> resultAction) {
        if (isPaused) {
            synchronized (queueLock) {
                viewActions.add(resultAction);
            }
        } else {
            viewActionSubject.onNext(resultAction);
        }
    }

    @Override
    public void resume() {
        isPaused = false;
        consumeQueue();
    }

    private void consumeQueue() {
        synchronized (queueLock) {
            final Iterator<Consumer<V>> actionIterator = viewActions.iterator();
            while (actionIterator.hasNext()) {
                final Consumer<V> pendingViewAction = actionIterator.next();
                viewActionSubject.onNext(pendingViewAction);
                actionIterator.remove();
            }
        }
    }

    @Override
    public void destroy() {
        disposables.clear();
        viewActionSubject.onComplete();
    }

    @Override
    public Observable<Consumer<V>> viewActionsObservable() {
        return viewActionSubject;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final ViewActionQueueImpl<?> that = (ViewActionQueueImpl<?>) o;

        if (isPaused != that.isPaused) {
            return false;
        }
        if (viewActions != null ? !viewActions.equals(that.viewActions) : that.viewActions != null) {
            return false;
        }
        if (queueLock != null ? !queueLock.equals(that.queueLock) : that.queueLock != null) {
            return false;
        }
        if (viewActionSubject != null ? !viewActionSubject.equals(that.viewActionSubject) : that.viewActionSubject != null) {
            return false;
        }
        return disposables != null ? !disposables.equals(that.disposables) : that.disposables != null;
    }

    @Override
    public int hashCode() {
        int result = viewActions != null ? viewActions.hashCode() : 0;
        result = 31 * result + (queueLock != null ? queueLock.hashCode() : 0);
        result = 31 * result + (viewActionSubject != null ? viewActionSubject.hashCode() : 0);
        result = 31 * result + (disposables != null ? disposables.hashCode() : 0);
        result = 31 * result + (isPaused ? 1 : 0);
        return result;
    }
}
