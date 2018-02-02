package com.wkw.commonbusiness.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wkw.commonbusiness.activity.MrActivity;
import com.wkw.commonbusiness.exception.DefaultErrorBundle;
import com.wkw.ext.utils.StringUtils;
import com.wkw.ext.utils.ToastUtils;

import timber.log.Timber;

/**
 * Created by wukewei on 2017/8/28.
 */

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends MrActivity
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
    public void showError(Throwable e) {
        if (!handleCommonResponseError((Exception) e)) {
            Timber.w(StringUtils.isEmpty(e.getMessage()) ? "未知错误" : e.getMessage());
            showErrorMessage(new DefaultErrorBundle((Exception) e));
        }
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }
}
