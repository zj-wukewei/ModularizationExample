package com.vongihealth.network.handler;

import android.content.Context;

/**
 * Created by GoGo on 2018-4-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public interface ResponseErrorListener {
    void handleResponseError(Context context, Throwable t);


    ResponseErrorListener EMPTY = new ResponseErrorListener() {
        @Override
        public void handleResponseError(Context context, Throwable t) {
        }
    };
}
