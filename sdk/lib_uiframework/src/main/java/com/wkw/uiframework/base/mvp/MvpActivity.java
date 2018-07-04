package com.wkw.uiframework.base.mvp;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wkw.ext.utils.ToastUtils;
import com.wkw.uiframework.base.BaseActivity;

import java.util.UUID;

/**
 * Created by GoGo on 2018-4-11.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends BaseActivity
        implements MvpView {

    private static final String TAG = "MvpActivity";
    private static final String KEY_VIEW_ID = "view_id";

    protected abstract P getPresenter();

    private String viewId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView(savedInstanceState);
        getPresenter().attachView((V) this);
    }

    private void initializeView(@Nullable final Bundle savedInstanceState) {
        viewId = (savedInstanceState == null) ? UUID.randomUUID().toString()
                : savedInstanceState.getString(KEY_VIEW_ID);
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
    public String getViewId() {
        return viewId;
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_VIEW_ID, viewId);
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
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }
}
