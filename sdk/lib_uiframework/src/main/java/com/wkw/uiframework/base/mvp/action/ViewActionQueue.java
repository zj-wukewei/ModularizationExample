package com.wkw.uiframework.base.mvp.action;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

/**
 * Created by GoGo on 2018-4-25.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public interface ViewActionQueue<View> {
    void subscribeTo(Observable<Consumer<View>> observable, Consumer<View> onCompleteAction, Consumer<Throwable> errorAction);

    void subscribeTo(Observable<Consumer<View>> observable, Consumer<Throwable> errorAction);

    void subscribeTo(Observable<Consumer<View>> observable);

    void subscribeTo(Completable completable, Consumer<View> onCompleteAction, Consumer<Throwable> errorAction);

    void subscribeTo(Single<Consumer<View>> single, Consumer<Throwable> errorAction);

    void subscribeTo(Single<Consumer<View>> single);

    void pause();

    void resume();

    void destroy();

    Observable<Consumer<View>> viewActionsObservable();
}
