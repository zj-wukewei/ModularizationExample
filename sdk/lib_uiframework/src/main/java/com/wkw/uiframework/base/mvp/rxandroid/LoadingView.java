package com.wkw.uiframework.base.mvp.rxandroid;

import com.wkw.uiframework.base.mvp.MvpView;

/**
 * Created by GoGo on 2018-5-8.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public interface LoadingView extends MvpView {
    void showLoading();

    void hideLoading();

    void showError(Exception e);
}
