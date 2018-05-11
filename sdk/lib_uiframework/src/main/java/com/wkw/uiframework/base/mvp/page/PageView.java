package com.wkw.uiframework.base.mvp.page;

import com.wkw.uiframework.base.mvp.rxandroid.LoadingView;

/**
 * Created by GoGo on 2018-5-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */


public interface PageView<T> extends LoadingView {
    void showData(PageEntity<T> data);
}


