package com.wkw.basic.interactor;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by wukewei on 2017/9/12.
 */

public class DefaultObserver<T> extends DisposableObserver<T> {
    @Override public void onNext(T t) {
        // no-op by default.
    }

    @Override public void onComplete() {
        // no-op by default.
    }

    @Override public void onError(Throwable exception) {
        // no-op by default.
    }
}
