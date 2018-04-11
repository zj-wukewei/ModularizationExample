package com.vongihealth.network.handler;

import android.content.Context;

/**
 * Created by GoGo on 2018-4-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class ErrorHandlerFactory {
    public final String TAG = "ErrorHandlerFactory";
    private Context mContext;
    private ResponseErrorListener mResponseErrorListener;

    public ErrorHandlerFactory(Context mContext, ResponseErrorListener mResponseErrorListener) {
        this.mResponseErrorListener = mResponseErrorListener;
        this.mContext = mContext;
    }

    /**
     * 处理错误
     *
     * @param throwable
     */
    public void handleError(Throwable throwable) {
        mResponseErrorListener.handleResponseError(mContext, throwable);
    }
}
