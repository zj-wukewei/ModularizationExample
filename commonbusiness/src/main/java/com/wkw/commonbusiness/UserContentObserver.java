package com.wkw.commonbusiness;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;

import com.wkw.commonbusiness.constant.AppConstants;
import com.wkw.commonbusiness.entity.TokenEntity;
import com.wkw.commonbusiness.utils.LoginModuleUtils;
import com.wkw.commonbusiness.utils.LoginModuleUtils.Observer;
import com.wkw.ext.utils.ToastUtils;

public class UserContentObserver extends ContentObserver {
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */

    private Context mContext;
    private Observer mObserver;

    public UserContentObserver(Context context, LoginModuleUtils.Observer observer) {
        super(null);
        this.mContext = context;
        this.mObserver = observer;
        loadTokenEntity();
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        loadTokenEntity();
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
                if (mObserver != null) {
                    mObserver.onChange(entity);
                }
            }
            cursor.close();
        } else {
            ToastUtils.show(mContext, "请安装登录模块");
        }
    }



}
