package com.wkw.archives.view;

import android.annotation.SuppressLint;

import com.vongihealth.live.Live;
import com.vongihealth.network.handler.RxErrorHandler;
import com.vongihealth.network.interactor.MrObserver;
import com.wkw.archives.domain.interactor.ArchivesListUseCase;
import com.wkw.archives.domain.interactor.NameUseCase;
import com.wkw.archives.domain.interactor.PasswordUseCase;
import com.wkw.commonbusiness.entity.TokenEntity;
import com.wkw.uiframework.base.mvp.MvpBasePresenter;
import com.wkw.uiframework.base.mvp.rxandroid.LoadingTransformer;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by wukewei on 2017/9/12.
 */

public class ArchivesPresenter extends MvpBasePresenter<ArchivesContract.View> implements ArchivesContract.Presenter {

    private final ArchivesListUseCase mArchivesListUseCase;
    private final RxErrorHandler mRxErrorHandler;
    @Inject
    NameUseCase mNameUseCase;
    @Inject
    PasswordUseCase mPasswordUseCase;

    @Inject
    public ArchivesPresenter(ArchivesListUseCase getArchivesListUseCase, RxErrorHandler handler) {
        this.mArchivesListUseCase = getArchivesListUseCase;
        mRxErrorHandler = handler;
    }

    @SuppressLint("CheckResult")
    @Override
    public void archivesList(int pn) {
        mArchivesListUseCase.execute(ArchivesListUseCase.Params.forArchives(pn))
                .compose(Live.bindLifecycle(getLifecycleOwner()))
                .subscribe(tokenEntity -> getView().showData(tokenEntity));
    }

    @Override
    public void fetchName() {
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void fetchPassword() {
    }

    private Consumer<ArchivesContract.View> toViewAction(String name) {
        return view -> view.showName(name);
    }


    private final class ArchivesObserver extends MrObserver<TokenEntity> {

        public ArchivesObserver(RxErrorHandler handler) {
            super(handler);
        }

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            getView().hideLoading();
        }

        @Override
        public void onComplete() {
            super.onComplete();
            getView().hideLoading();
        }

        @Override
        public void onNext(TokenEntity entity) {
            super.onNext(entity);
            getView().showData(entity);
        }
    }
}
