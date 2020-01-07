package com.wkw.modularization;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.wkw.uiframework.base.SwipeBackActivity;

/**
 * Created by GoGo on 2018-3-19.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class BackActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back_activity);
    }
}
