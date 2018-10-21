package com.github.wkw.login;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.wkw.commonbusiness.activity.MrActivity;
import com.wkw.commonbusiness.constant.AppConstats;

import java.util.UUID;

import javax.inject.Inject;

import dagger.android.AndroidInjection;


/**
 * @author GoGo on 2018/10/21.
 */

public class LoginActivity extends MrActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private Button mBtnLogin, mBtnLoginOut;
    @Inject
    SharedPreferences  mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_login);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLoginOut = findViewById(R.id.btn_login_out);
        mBtnLogin.setOnClickListener(this);
        mBtnLoginOut.setOnClickListener(this);
    }

    @Override
    protected String pageName() {
        return TAG;
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if (id == R.id.btn_login) {
            String token  = UUID.randomUUID().toString();
            String uId = "123456";
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(AppConstats.TOKEN, token);
            editor.putString(AppConstats.UID, uId);
            editor.apply();
            getContentResolver().notifyChange(Uri.parse(AppConstats.USER_URI), null);

        } else if (id == R.id.btn_login_out) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putString(AppConstats.TOKEN, null);
            editor.putString(AppConstats.UID, null);
            editor.apply();
            getContentResolver().notifyChange(Uri.parse(AppConstats.USER_URI), null);
        }
    }

}
