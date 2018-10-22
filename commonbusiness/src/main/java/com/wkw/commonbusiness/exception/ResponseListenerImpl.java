package com.wkw.commonbusiness.exception;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.vongihealth.network.exception.ResponseException;
import com.vongihealth.network.handler.ResponseErrorListener;
import com.wkw.commonbusiness.constant.AppConstants;
import com.wkw.ext.utils.ToastUtils;
import com.wkw.uiframework.error.ErrorMessageFactory;

import java.util.List;

import timber.log.Timber;

/**
 * Created by GoGo on 2018-4-11.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class ResponseListenerImpl implements ResponseErrorListener {
    @Override
    public void handleResponseError(Context context, Throwable t) {
        if (t instanceof ResponseException) {
            if (((ResponseException) t).getStatusCode() == ResponseException.ERROR_CODE_NEED_LOGIN) {
                Timber.e(t, "请重新登录");
                if (schemeLoginValid(context)) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(AppConstants.LOGIN_URI));
                    context.startActivity(intent);
                }
            }
        }
        final String msg = ErrorMessageFactory.create(context, (Exception) t);
        ToastUtils.show(context, msg);
    }

    private boolean schemeLoginValid(Context context) {
        PackageManager manager = context.getPackageManager();
        Intent action = new Intent(Intent.ACTION_VIEW);
        action.setData(Uri.parse(AppConstants.LOGIN_URI));
        List list = manager.queryIntentActivities(action, PackageManager.GET_RESOLVED_FILTER);
        return list != null && list.size() > 0;
    }
}
