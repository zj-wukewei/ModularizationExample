package com.wkw.archives.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wkw.archives.R;
import com.wkw.commonbusiness.entity.TokenEntity;
import com.wkw.commonbusiness.entity.UserSystem;
import com.wkw.commonbusiness.module.knowledge.KnowledgeProxy;
import com.wkw.uiframework.base.mvp.MvpActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import timber.log.Timber;

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
        Timber.d(userSystem.toString());
        Timber.d(KnowledgeProxy.g.getServiceInterface().getModuleName());
        getPresenter().archivesList(0);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showData(TokenEntity entity) {

    }

    @Override
    public void hideLoading() {

    }
}
