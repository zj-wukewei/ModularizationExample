package com.wkw.archives.view;

import com.vongihealth.network.handler.RxErrorHandler;
import com.vongihealth.network.interactor.MrObserver;
import com.wkw.archives.domain.interactor.ArchivesListUseCase;
import com.wkw.commonbusiness.entity.TokenEntity;
import com.wkw.uiframework.base.mvp.MvpBasePresenter;

import javax.inject.Inject;

/**
 * Created by wukewei on 2017/9/12.
 */

public class ArchivesPresenter extends MvpBasePresenter<ArchivesContract.View> implements ArchivesContract.Presenter {

    private final ArchivesListUseCase mArchivesListUseCase;
    private final RxErrorHandler mRxErrorHandler;

    @Inject
    public ArchivesPresenter(ArchivesListUseCase getArchivesListUseCase, RxErrorHandler handler) {
        this.mArchivesListUseCase = getArchivesListUseCase;
        mRxErrorHandler = handler;
    }

    @Override
    public void archivesList(int pn) {
        getView().showLoading();
        mArchivesListUseCase.execute(new ArchivesObserver(mRxErrorHandler), ArchivesListUseCase.Params.forArchives(pn));
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
