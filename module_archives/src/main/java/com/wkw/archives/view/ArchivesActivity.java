package com.wkw.archives.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wkw.archives.R;
import com.wkw.archives.domain.entity.ArchivesEntity;
import com.wkw.commonbusiness.entity.UserSystem;
import com.wkw.commonbusiness.module.knowledge.KnowledgeProxy;
import com.wkw.commonbusiness.mvp.MvpActivity;
import com.wkw.sdk.utils.Logger;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by wukewei on 2017/9/9.
 */

public class ArchivesActivity extends MvpActivity<ArchivesContract.View, ArchivesContract.Presenter> implements ArchivesContract.View {

    private static final String TAG = "ArchivesActivity";

    @Inject
    UserSystem userSystem;

    @Inject
    ArchivesPresenter mArchivesPresenter;

    @Override
    protected ArchivesContract.Presenter getPresenter() {
        return mArchivesPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.archives_activity_archives);
        Logger.d(TAG, userSystem.toString());
        Logger.d(TAG, KnowledgeProxy.g.getServiceInterface().getModuleName());
        getPresenter().archivesList(0);
    }


    @Override
    protected String pageName() {
        return TAG;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showData(ArchivesEntity entity) {

    }

    @Override
    public void hideLoading() {

    }
}
