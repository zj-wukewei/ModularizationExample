package com.wkw.uiframework.error;

import android.content.Context;

import com.vongihealth.network.exception.NetworkConnectionException;
import com.vongihealth.network.exception.ResponseException;
import com.wkw.ext.Ext;
import com.wkw.ext.utils.NetWorkUtils;
import com.wkw.ext.utils.StringUtils;
import com.wkw.uiframework.R;

import timber.log.Timber;

/**
 * Created by wukewei on 2017/8/28.
 */

public class ErrorMessageFactory {

    private static final String TAG = "ErrorMessageFactory";

    public static String create(Context context, Exception exception) {
        if (StringUtils.isNotEmpty(exception.getMessage())) {
            Timber.d(exception.getMessage());
        }
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException || !NetWorkUtils.isNetworkConnected()) {
            message = context.getString(R.string.exception_message_no_connection);
        } else if (exception instanceof ResponseException) {
            message = exception.getMessage();
        } else if (Ext.g().isDebuggable()) {
            message = exception.getMessage();
        }

        return message;
    }
}
