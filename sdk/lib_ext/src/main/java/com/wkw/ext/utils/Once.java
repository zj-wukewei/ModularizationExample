package com.wkw.ext.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hzwukewei on 2016/4/22.
 */
public class Once {

    SharedPreferences mSharedPreferences;
    Context mContext;


    public Once(Context context) {
        mSharedPreferences = context.getSharedPreferences("once", Context.MODE_PRIVATE);
        mContext = context;
    }


    public void show(String tagKey, OnceCallback callback) {
        boolean isSecondTime = mSharedPreferences.getBoolean(tagKey, false);
        if (!isSecondTime) {
            callback.onOnce();
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(tagKey, true);
            editor.apply();
        }
    }

    public void show(String tagKey, OnceCallback callback, NotOnceCallback notOnceCallback) {
        boolean isSecondTime = mSharedPreferences.getBoolean(tagKey, false);
        if (!isSecondTime) {
            callback.onOnce();
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putBoolean(tagKey, true);
            editor.apply();
        } else {
            notOnceCallback.notOnOnce();
        }
    }


    public void show(int tagKeyResId, OnceCallback callback) {
        show(mContext.getString(tagKeyResId), callback);
    }


    public interface OnceCallback {
        void onOnce();
    }

    public interface NotOnceCallback {
        void notOnOnce();
    }
}
