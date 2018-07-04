package com.wkw.uiframework.base.mvp;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wkw.ext.utils.ToastUtils;
import com.wkw.uiframework.base.BaseFragment;

import java.util.UUID;

/**
 * Created by GoGo on 2018-5-9.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends BaseFragment implements MvpView {

    private static final String TAG = "MvpFragment";
    private static final String KEY_VIEW_ID = "view_id";

    protected abstract P getPresenter();

    private String viewId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView(savedInstanceState);
        getPresenter().attachView((V) this);
    }

    private void initializeView(@Nullable final Bundle savedInstanceState) {
        viewId = (savedInstanceState == null) ? UUID.randomUUID().toString()
                : savedInstanceState.getString(KEY_VIEW_ID);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_VIEW_ID, viewId);
    }

    @Override
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
        getPresenter().destroy();
    }

    @Override
    public String getViewId() {
        return viewId;
    }

    @Override
    public void showToast(int stringId) {
        ToastUtils.show(getActivity(), stringId);
    }


    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }
}
