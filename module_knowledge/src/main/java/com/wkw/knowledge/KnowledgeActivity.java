package com.wkw.knowledge;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wkw.commonbusiness.activity.MrActivity;
import com.wkw.commonbusiness.entity.UserSystem;
import com.wkw.sdk.utils.Logger;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by wukewei on 2017/9/9.
 */

public class KnowledgeActivity extends MrActivity {

    private static final String TAG = "KnowledgeActivity";

    @Inject
    UserSystem userSystem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_activity_knowledge);
        Logger.d(TAG, userSystem.toString());
    }

    @Override
    protected String pageName() {
        return TAG;
    }
}
