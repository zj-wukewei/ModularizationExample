package com.wkw.uiframework.base.mvp.rxandroid;

import com.wkw.ext.utils.guava.Preconditions;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

/**
 * Created by GoGo on 2018-5-8.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class LoadingTransformer<T> implements ObservableTransformer<T, T> {

    private final LoadingView mLoadingView;

    public LoadingTransformer(LoadingView loadingView) {
        Preconditions.checkNotNull(loadingView, "loadingView == null");
        mLoadingView = loadingView;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.doOnSubscribe(disposable -> mLoadingView.showLoading())
                .doOnError(throwable -> mLoadingView.showError((Exception) throwable))
                .doOnComplete(mLoadingView::hideLoading);
    }
}
