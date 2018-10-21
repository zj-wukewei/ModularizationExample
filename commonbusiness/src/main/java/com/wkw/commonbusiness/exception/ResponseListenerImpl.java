package com.wkw.commonbusiness.exception;

import android.content.Context;

import com.vongihealth.network.exception.ResponseException;
import com.vongihealth.network.handler.ResponseErrorListener;
import com.wkw.ext.utils.ToastUtils;
import com.wkw.uiframework.error.ErrorMessageFactory;

import timber.log.Timber;

/**
 * Created by GoGo on 2018-4-11.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class ResponseListenerImpl implements ResponseErrorListener {
    @Override
    public void handleResponseError(Context context, Throwable t) {
        Timber.e(t);
        if (t instanceof ResponseException) {

        }
        final String msg = ErrorMessageFactory.create(context, (Exception) t);
        ToastUtils.show(context, msg);
    }
}
