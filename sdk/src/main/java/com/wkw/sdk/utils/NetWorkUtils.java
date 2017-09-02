package com.wkw.sdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wkw.sdk.Ext;

/**
 * Created by wukewei on 2017/8/28.
 */

public class NetWorkUtils {

    private static final String TAG = "NetWorkUtils";

    /**
     * 检查当前网络是否连接
     */
    public static boolean isNetworkConnected() {
        return isNetworkConnected(Ext.getContext());
    }

    /**
     * 检查当前网络是否连接
     *
     * @param context Application context.
     * @return 当前网络是否连接
     */
    public static boolean isNetworkConnected(Context context) {
        NetworkInfo info = getActiveNetworkInfo(context);
        return info != null && info.isConnected();
    }

    public static boolean isWifiConnected() {
        return isWifiConnected(Ext.getContext());
    }

    /**
     * 检查当前Wifi网络是否连接
     *
     * @param context Application context.
     * @return Whether wifi network is connected.
     */
    public static boolean isWifiConnected(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 检查当前移动网络是否连接
     *
     * @param context Application context.
     * @return Whether mobile network is connected.
     */

    public static boolean isMobileConnected(Context context) {
        if (context == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo(context);
        return activeNetworkInfo != null && activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    public static NetworkInfo getActiveNetworkInfo(Context context) {
        try {
            ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            return connMgr.getActiveNetworkInfo();
        } catch (Throwable e) {
            Logger.e(TAG, "fail to get active network info" + e.getMessage());
            return null;
        }
    }
}
