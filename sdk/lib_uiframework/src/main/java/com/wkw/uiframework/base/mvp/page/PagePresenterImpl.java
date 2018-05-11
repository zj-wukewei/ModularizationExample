package com.wkw.uiframework.base.mvp.page;

import com.wkw.uiframework.base.mvp.MvpBasePresenter;
import com.wkw.uiframework.base.mvp.rxandroid.LoadingTransformer;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by GoGo on 2018-5-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public abstract class PagePresenterImpl<Request, Response, V extends PageView<Response>> extends MvpBasePresenter<V> implements PagePresenter<Request, Response, V> {
    @Override
    public void fetchData(Request request) {
        viewActionQueue.subscribeTo(provideSource(request)
                .compose(new LoadingTransformer<>(getView()))
                .map(this::toViewAction));
    }

    public abstract Observable<PageEntity<Response>> provideSource(Request request);

    private Consumer<V> toViewAction(PageEntity<Response> response) {
        return v -> v.showData(response);
    }

}
