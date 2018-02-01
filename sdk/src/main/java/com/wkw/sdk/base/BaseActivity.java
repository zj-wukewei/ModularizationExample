package com.wkw.sdk.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.wkw.sdk.utils.AppManager;

/**
 * Created by wukewei on 2017/8/28.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }


    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected String getParam(@NonNull String key) {
        Intent intent = getIntent();
        if (intent != null) {
            return intent.getStringExtra(key);
        }
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
        if (getExitAnimation() > 0) {
            overridePendingTransition(0, getExitAnimation());
        }

    }

    protected int getExitAnimation() {
        return 0;
    }
}
