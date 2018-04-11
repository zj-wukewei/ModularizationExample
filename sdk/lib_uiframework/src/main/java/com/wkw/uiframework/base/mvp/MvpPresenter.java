package com.wkw.uiframework.base.mvp;

/**
 * Created by GoGo on 2018-4-11.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

    V getView();

    void resume();

    void pause();

    void destroy();

}
