package com.wkw.commonbusiness.entity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;

import com.wkw.commonbusiness.constant.AppConstants;
import com.wkw.ext.utils.ToastUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by wukewei on 2017/8/25.
 */

@Singleton
public class UserSystem {
    private static final String KEY_TOKEN = "token";
    private static final String KEY_USER_ID = "uid";

    private BehaviorSubject<TokenEntity> mTokenEntityBehaviorSubject = BehaviorSubject.create();

    private Context mContext;

    @Inject
    public UserSystem(Context context) {
        this.mContext = context;
        loadTokenEntity();
        context.getContentResolver().registerContentObserver(Uri.parse(AppConstants.USER_URI), false, new ContentObserver(null) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                loadTokenEntity();
            }
        });
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
                mTokenEntityBehaviorSubject.onNext(entity);
            }
            cursor.close();
        } else {
            ToastUtils.show(mContext, "请安装登录模块");
        }
    }

    public Observable<TokenEntity> getTokenEntityObservable() {
        return mTokenEntityBehaviorSubject;
    }
}
