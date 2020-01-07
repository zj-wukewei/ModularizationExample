package com.wkw.uiframework.base.mvp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

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

    protected abstract P getPresenter();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().attachView((V) this);
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
    public void onDestroy() {
        super.onDestroy();
        getPresenter().detachView();
        getPresenter().destroy();
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
