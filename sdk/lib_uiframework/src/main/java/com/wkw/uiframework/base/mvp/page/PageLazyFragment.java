package com.wkw.uiframework.base.mvp.page;

import android.os.Bundle;

/**
 * 懒加载的ListFragment
 * Created by GoGo on 2018-5-11.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public abstract class PageLazyFragment<Request, Response, V extends PageView<Response>, P extends PagePresenter<Request, Response, V>> extends PageFragment<Request, Response, V, P> {

    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }


    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    /**
     * 准备去取数据
     *
     * @param forceUpdate 是否强制刷新数据
     * @return
     */
    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            getPresenter().fetchData(provideRequest());
            isDataInitiated = true;
            return true;
        }
        return false;
    }

}