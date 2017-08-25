package com.wkw.sdk.utils;

import android.util.Log;

/**
 * Created by wukewei on 2017/8/25.
 */

public class Logger {
    /**
     * This flag to indicate the log is enabled or disabled.
     */
    public static boolean isLogEnable = true;


    /**
     * Debug
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (isLogEnable) {
            Log.d(tag, msg);
        }
    }

    /**
     * Information
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (isLogEnable) {
            Log.i(tag, msg);
        }
    }

    /**
     * Verbose
     *
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (isLogEnable) {
            Log.v(tag, msg);
        }
    }

    /**
     * Warning
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (isLogEnable) {
            Log.w(tag, msg);
        }
    }

    /**
     * Error
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (isLogEnable) {
            Log.e(tag, msg);
        }
    }

}
