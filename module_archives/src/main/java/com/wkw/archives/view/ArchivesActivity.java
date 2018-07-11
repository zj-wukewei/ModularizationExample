package com.wkw.archives.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.vongihealth.live.Live;
import com.wkw.archives.R;
import com.wkw.commonbusiness.entity.TokenEntity;
import com.wkw.commonbusiness.module.knowledge.KnowledgeProxy;
import com.wkw.uiframework.base.mvp.MvpActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by wukewei on 2017/9/9.
 */

public class ArchivesActivity extends MvpActivity<ArchivesContract.View, ArchivesContract.Presenter> implements ArchivesContract.View {

    private static final String TAG = "ArchivesActivity";

    @Inject
    ArchivesPresenter mArchivesPresenter;


    Button bu;
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
        getPresenter().archivesList(1);
        bu = findViewById(R.id.btn);
        findViewById(R.id.btn).setOnClickListener(view -> {
            KnowledgeProxy.g.getUiInterface().goToKnowledgeActivity(ArchivesActivity.this);
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
