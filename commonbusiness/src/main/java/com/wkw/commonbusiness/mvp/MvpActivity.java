package com.wkw.commonbusiness.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wkw.commonbusiness.activity.MrActivity;
import com.wkw.commonbusiness.exception.DefaultErrorBundle;
import com.wkw.sdk.utils.Logger;
import com.wkw.sdk.utils.StringUtils;
import com.wkw.sdk.utils.ToashUtils;

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
        ToashUtils.show(this, stringId);
    }

    @Override
    public void showError(Throwable e) {
        if (!handleCommonResponseError((Exception) e)) {
            Logger.w(this.getLocalClassName(), StringUtils.isEmpty(e.getMessage()) ? "未知错误" : e.getMessage());
            showErrorMessage(new DefaultErrorBundle((Exception) e));
        }
    }

    @Override
    public Context context() {
        return getApplicationContext();
    }
}
