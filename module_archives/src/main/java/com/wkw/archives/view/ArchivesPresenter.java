package com.wkw.archives.view;

import com.wkw.archives.domain.entity.ArchivesEntity;
import com.wkw.archives.domain.interactor.ArchivesListUseCase;
import com.wkw.basic.interactor.DefaultObserver;
import com.wkw.commonbusiness.mvp.MvpBasePresenter;

import javax.inject.Inject;

/**
 * Created by wukewei on 2017/9/12.
 */

public class ArchivesPresenter extends MvpBasePresenter<ArchivesContract.View> implements ArchivesContract.Presenter {

    private final ArchivesListUseCase mArchivesListUseCase;


    @Inject
    public ArchivesPresenter(ArchivesListUseCase getArchivesListUseCase) {
        this.mArchivesListUseCase = getArchivesListUseCase;
    }

    @Override
    public void archivesList(int pn) {
        getView().showLoading();
        mArchivesListUseCase.execute(new ArchivesObserver(), ArchivesListUseCase.Params.forArchives(pn));
    }


    private final class ArchivesObserver extends DefaultObserver<ArchivesEntity> {

        @Override
        public void onError(Throwable exception) {
            super.onError(exception);
            getView().showError(exception);
            getView().hideLoading();
        }

        @Override
        public void onComplete() {
            super.onComplete();
            getView().hideLoading();
        }

        @Override
        public void onNext(ArchivesEntity entity) {
            super.onNext(entity);
            getView().showData(entity);
        }
    }
}
