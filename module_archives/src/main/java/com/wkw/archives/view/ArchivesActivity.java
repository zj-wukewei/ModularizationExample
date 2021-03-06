package com.wkw.archives.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.wkw.archives.R;
import com.wkw.archives.dialog.TestDialog;
import com.wkw.commonbusiness.entity.TokenEntity;
import com.wkw.commonbusiness.entity.UserSystem;
import com.wkw.uiframework.base.mvp.MvpActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Created by wukewei on 2017/9/9.
 */

public class ArchivesActivity extends MvpActivity<ArchivesContract.View, ArchivesContract.Presenter> implements ArchivesContract.View {

    private static final String TAG = "ArchivesActivity";

    @Inject
    ArchivesPresenter mArchivesPresenter;

    @Inject
    UserSystem mUserSystem;
    private CompositeDisposable mDisposables = new CompositeDisposable();


    @Override
    protected ArchivesContract.Presenter getPresenter() {
        return mArchivesPresenter;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.archives_activity_archives);
        initView();
        getPresenter().fetchPassword();
    }

    private void initView() {
        findViewById(R.id.btn).setOnClickListener(v -> {
            if (mUserSystem.hasLogin()) {
                Timber.d("用户已经登陆 %s", mUserSystem.getTokenEntity().toString());

            } else {
                Timber.d("用户未登陆");

            }
            TestDialog dialog = new TestDialog();
            dialog.show(getSupportFragmentManager(), dialog.getTag());
        });
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
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDisposables.clear();
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
        Timber.d("showPassword %s", pa);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposables.dispose();
    }
}
