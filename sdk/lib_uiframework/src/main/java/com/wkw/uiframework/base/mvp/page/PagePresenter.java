package com.wkw.uiframework.base.mvp.page;

import com.wkw.uiframework.base.mvp.MvpPresenter;


/**
 * Created by GoGo on 2018-5-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public interface PagePresenter<Request, Response, V extends PageView<Response>> extends MvpPresenter<V> {
    void fetchData(Request request);
}
