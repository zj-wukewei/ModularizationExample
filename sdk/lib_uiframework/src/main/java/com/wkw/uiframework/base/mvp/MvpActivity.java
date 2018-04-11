package com.wkw.uiframework.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wkw.ext.utils.ToastUtils;
import com.wkw.uiframework.base.BaseActivity;

/**
 * Created by GoGo on 2018-4-11.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends BaseActivity
        implements MvpView {

    private static final String TAG = "MvpActivity";

    protected abstract P getPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().attachView((V) this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
        getPresenter().destroy();
    }

    @Override
    public void showToast(int stringId) {
        ToastUtils.show(this, stringId);
    }


    @Override
    public Context context() {
        return getApplicationContext();
    }
}
