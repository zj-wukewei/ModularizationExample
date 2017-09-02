package com.wkw.commonbusiness.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wkw.commonbusiness.entity.UserSystem;
import com.wkw.commonbusiness.exception.ErrorBundle;
import com.wkw.basic.exception.ResponseException;
import com.wkw.commonbusiness.exception.ErrorMessageFactory;
import com.wkw.sdk.base.BaseActivity;
import com.wkw.sdk.utils.StringUtils;
import com.wkw.sdk.utils.ToashUtils;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by wukewei on 2017/8/28.
 */

public abstract class MrActivity extends BaseActivity {

    @Inject
    UserSystem mUserSystem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    public UserSystem getUserSystem() {
        return mUserSystem;
    }

    protected boolean handleCommonResponseError(Exception exception) {
        boolean handled = false;

        if (exception instanceof ResponseException) {
            final ResponseException responseException = (ResponseException) exception;
            switch (responseException.getStatusCode()) {
                case ResponseException.ERROR_CODE_NEED_PERFECT_PROFILE:
                    handled = true;
                    break;
            }
        }

        return handled;
    }

    protected void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(getApplicationContext(), errorBundle.getException());
        showErrorMessage(errorMessage);
    }

    private void showErrorMessage(String errorMessage) {
        if (StringUtils.isNotEmpty(errorMessage)) {
            ToashUtils.show(getApplicationContext(), errorMessage);
        }
    }

    protected abstract String pageName();
}
