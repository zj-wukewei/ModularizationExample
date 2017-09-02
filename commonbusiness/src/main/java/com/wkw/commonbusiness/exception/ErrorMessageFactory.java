package com.wkw.commonbusiness.exception;

import android.content.Context;

import com.wkw.basic.exception.NetworkConnectionException;
import com.wkw.basic.exception.ResponseException;
import com.wkw.basic.network.MrService;
import com.wkw.commonbusiness.R;
import com.wkw.sdk.utils.Logger;
import com.wkw.sdk.utils.NetWorkUtils;
import com.wkw.sdk.utils.StringUtils;

/**
 * Created by wukewei on 2017/8/28.
 */

public class ErrorMessageFactory {

    private static final String TAG = "ErrorMessageFactory";

    public static String create(Context context, Exception exception) {
        if (StringUtils.isNotEmpty(exception.getMessage())) {
            Logger.d(TAG, exception.getMessage());
        }
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException || !NetWorkUtils.isNetworkConnected()) {
            message = context.getString(R.string.exception_message_no_connection);
        } else if (exception instanceof ResponseException) {
            message = exception.getMessage();
        } else if (MrService.INITIAL_ENVIRONMENT_DEV) {
            message = exception.getMessage();
        }

        return message;
    }
}
