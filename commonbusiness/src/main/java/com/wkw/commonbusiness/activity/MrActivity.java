package com.wkw.commonbusiness.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.vongihealth.network.exception.ErrorBundle;
import com.vongihealth.network.exception.ResponseException;
import com.wkw.ext.utils.StringUtils;
import com.wkw.ext.utils.ToastUtils;
import com.wkw.uiframework.base.BaseActivity;
import com.wkw.uiframework.error.ErrorMessageFactory;

/**
 * Created by wukewei on 2017/8/28.
 */

public abstract class MrActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            ToastUtils.show(getApplicationContext(), errorMessage);
        }
    }

    protected abstract String pageName();
}
