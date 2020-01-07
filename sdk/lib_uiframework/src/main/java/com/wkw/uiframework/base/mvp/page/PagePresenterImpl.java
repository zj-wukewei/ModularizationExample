package com.wkw.uiframework.base.mvp.page;

import android.annotation.SuppressLint;

import com.vongihealth.network.interactor.UseCase;
import com.wkw.uiframework.base.mvp.MvpBasePresenter;
import com.wkw.uiframework.base.mvp.rxandroid.LoadingTransformer;

/**
 * Created by GoGo on 2018-5-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public abstract class PagePresenterImpl<Request, Response, V extends PageView<Response>> extends MvpBasePresenter<V> implements PagePresenter<Request, Response, V> {


    @Override
    public void attachView(V view) {
        super.attachView(view);
        getLifecycle().addObserver(providerSource());
    }

    @SuppressLint("CheckResult")
    @Override
    public void fetchData(Request request) {
        providerSource()
                .execute(request)
                .compose(new LoadingTransformer<>(getView()))
                .subscribe(it -> getView().showData(it),
                        throwable -> getRxErrorHandler().getHandlerFactory().handleError(throwable));
    }

    public abstract UseCase<PageEntity<Response>, Request> providerSource();


}
