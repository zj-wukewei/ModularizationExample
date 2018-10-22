package com.wkw.commonbusiness.utils;


import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;

import com.wkw.commonbusiness.constant.AppConstants;
import com.wkw.commonbusiness.entity.TokenEntity;
import com.wkw.ext.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GoGo on 2018/10/22.
 */

public class LoginModuleUtils {


    private static class SingletonHolder {
        private final static LoginModuleUtils instance = new LoginModuleUtils();
    }

    public static LoginModuleUtils getInstance() {
        return SingletonHolder.instance;
    }

    public static final LoginModuleUtils instance = new LoginModuleUtils();

    private List<Observer> mObservers = new ArrayList<>();
    private Context mContext;



    public void initContentResolver(Context context) {
        if (mContext == null) {
            this.mContext = context.getApplicationContext();
            context.getContentResolver().registerContentObserver(Uri.parse(AppConstants.USER_URI), false, new ContentObserver(null) {
                @Override
                public void onChange(boolean selfChange) {
                    super.onChange(selfChange);
                    loadTokenEntity();
                }
            });
        }
    }

    private void loadTokenEntity() {
        ContentResolver resolver = mContext.getContentResolver();
        Uri uri = Uri.parse(AppConstants.USER_URI);
        Cursor cursor = resolver.query(uri, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String token = cursor.getString(0);
                String uId = cursor.getString(1);
                TokenEntity entity = new TokenEntity(uId, token);
                notifyTokenEntity(entity);
            }
            cursor.close();
        } else {
            ToastUtils.show(mContext, "请安装登录模块");
        }
    }

    public void registerObserver(Observer observer) {
        if (observer != null && !mObservers.contains(observer)) {
            mObservers.add(observer);
        }
    }

    public void unregisterObserver(Observer observer) {
        if (observer != null) {
            mObservers.remove(observer);
        }
    }


    private void notifyTokenEntity(TokenEntity entity) {
        for (Observer mObserver : mObservers) {
            mObserver.onChange(entity);
        }
    }


    public interface Observer {
        void onChange(TokenEntity entity);
    }
}
