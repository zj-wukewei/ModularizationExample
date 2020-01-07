package com.github.wkw.login.provider;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import androidx.annotation.Nullable;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.wkw.commonbusiness.constant.AppConstants;

import javax.inject.Inject;

import dagger.android.DaggerContentProvider;
import io.reactivex.annotations.NonNull;

/**
 * @author GoGo on 2018/10/21.
 */

public class UserContentProvider extends DaggerContentProvider {


    private static final int SUCCESS = 1;

    private final String[] columnNames = new String[]{AppConstants.TOKEN, AppConstants.UID};
    static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        mUriMatcher.addURI("com.modularization.android.login", "user", SUCCESS);    //uri规则可自己定义，但一定和清单文件一直
    }

    @Inject
    RxSharedPreferences mRxSharedPreferences;


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        String token = mRxSharedPreferences.getString(AppConstants.TOKEN).get();
        String uId = mRxSharedPreferences.getString(AppConstants.UID).get();
        MatrixCursor cursor = new MatrixCursor(columnNames);
        cursor.addRow(new String[] {token, uId});
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
