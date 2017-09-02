package com.wkw.commonbusiness.mvp;

/**
 * Created by wukewei on 2017/8/28.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();

    V getView();

    void resume();

    void pause();

    void destroy();

}
