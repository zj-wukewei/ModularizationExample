package com.wkw.uiframework.base.mvp.page;

import android.annotation.SuppressLint;

import com.vongihealth.live.Live;
import com.wkw.uiframework.base.mvp.MvpBasePresenter;
import com.wkw.uiframework.base.mvp.rxandroid.LoadingTransformer;

import io.reactivex.Observable;

/**
 * Created by GoGo on 2018-5-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public abstract class PagePresenterImpl<Request, Response, V extends PageView<Response>> extends MvpBasePresenter<V> implements PagePresenter<Request, Response, V> {
    @SuppressLint("CheckResult")
    @Override
    public void fetchData(Request request) {
        // TODO: 2018/7/11 暂时先加上 getLifecycleOwner，有时间再研究要不要加
        provideSource(request)
                .compose(new LoadingTransformer<>(getView()))
                .compose(Live.bindLifecycle(getLifecycleOwner()))
                .subscribe(it -> getView().showData(it),
                        throwable -> getRxErrorHandler().getHandlerFactory().handleError(throwable));

    }

    public abstract Observable<PageEntity<Response>> provideSource(Request request);

}
