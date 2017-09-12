package com.wkw.archives;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wkw.commonbusiness.activity.MrActivity;
import com.wkw.commonbusiness.entity.UserSystem;
import com.wkw.commonbusiness.module.knowledge.KnowledgeProxy;
import com.wkw.sdk.utils.Logger;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by wukewei on 2017/9/9.
 */

public class ArchivesActivity extends MrActivity {

    private static final String TAG = "ArchivesActivity";

    @Inject
    UserSystem userSystem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.archives_activity_archives);
        Logger.d(TAG, userSystem.toString());
        Logger.d(TAG, KnowledgeProxy.g.getServiceInterface().getModuleName());
    }

    @Override
    protected String pageName() {
        return null;
    }
}
