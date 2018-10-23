package com.wkw.commonbusiness.utils;


import android.content.Context;
import android.net.Uri;

import com.wkw.commonbusiness.UserContentObserver;
import com.wkw.commonbusiness.constant.AppConstants;
import com.wkw.commonbusiness.entity.TokenEntity;

/**
 * @author GoGo on 2018/10/22.
 */

public class LoginModuleUtils {

   public static void registerUserContentObserver(Context context, UserContentObserver observer) {
       context.getContentResolver().registerContentObserver(Uri.parse(AppConstants.USER_URI), false, observer);
   }

    public static void unRegisterUserContentObserver(Context context, UserContentObserver observer) {
        context.getContentResolver().unregisterContentObserver(observer);
    }

    public interface Observer {
        void onChange(TokenEntity entity);
    }
}
