package com.wkw.archives.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.f2prateek.rx.preferences2.RxSharedPreferences;
import com.vongihealth.network.executor.PostExecutionThread;
import com.wkw.archives.R;
import com.wkw.commonbusiness.entity.TokenEntity;
import com.wkw.commonbusiness.entity.UserSystem;
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

    @Inject
    PostExecutionThread mExecutor;

    @Inject
    RxSharedPreferences mRxSharedPreferences;

    private TextView mTvName;
    private TextView mTvPassword;


    @Override
    protected ArchivesContract.Presenter getPresenter() {
        return mArchivesPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.archives_activity_archives);
        mTvName = findViewById(R.id.tv);
        mTvPassword = findViewById(R.id.password);
        findViewById(R.id.btn).setOnClickListener(v -> startActivity(new Intent(ArchivesActivity.this, ArActivity.class)));
        mArchivesPresenter.fetchName();
        mArchivesPresenter.fetchPassword();
    }


    @Override
    public void showLoading() {
        Timber.d("showLoading");
    }

    @Override
    public void showData(TokenEntity entity) {

    }

    @Override
    public void showDataString(String entity) {

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
        mTvName.setText(name);
    }

    @Override
    public void showPassword(String pa) {
        Timber.d(pa);
        mTvPassword.setText(pa);
    }

}
