package com.wkw.archives.view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.wkw.archives.R;
import com.wkw.commonbusiness.activity.MrActivity;

/**
 * Created by GoGo on 2018-5-8.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class ArActivity extends MrActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.archives_activity_ar);
    }

    @Override
    protected String pageName() {
        return null;
    }
}
