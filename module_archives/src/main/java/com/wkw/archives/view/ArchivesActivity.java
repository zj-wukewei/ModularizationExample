package com.wkw.archives.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wkw.archives.R;
import com.wkw.commonbusiness.entity.TokenEntity;
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
    }


    @Override
    public void showLoading() {
        Timber.d("showLoading");
    }

    @Override
    public void showData(TokenEntity entity) {
        Timber.d("ssss %s", entity.toString());
    }

    @Override
    public void showDataString(String entity) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.d("onStop");
    }

    @Override
    public void hideLoading() {
        Timber.d("hideLoading");
    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void showName(String name) {
        Timber.d(name);
    }

    @Override
    public void showPassword(String pa) {
        Timber.d(pa);
    }

}
