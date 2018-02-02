package com.wkw.ext.utils;

import android.os.Build;

/**
 * Created by hzwukewei on 2017-2-24.
 */

public final class SdkVersionUtils {
    /**
     * hasIceCreamSandwich
     *
     * @return true false
     */
    public static boolean hasIceCreamSandwich() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }
    /**
     * hasJellyBean
     *
     * @return true false
     */
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }
    /**
     * 4.2以上
     *
     * @return true false
     */
    public static boolean hasJellyBeanMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }
    /**
     * 4.3及以上
     *
     * @return 4.3及以上
     */
    public static boolean hasJellyBeanMR2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }
    /**
     * 4.4及以上
     *
     * @return 4.4及以上
     */
    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }
    /**
     * 5.0及以上
     *
     * @return 5.0及以上
     */
    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
    /**
     * 6.0及以上
     *
     * @return 6.0及以上
     */
    public static boolean hasMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

}
