package com.wkw.knowledge.view;

import android.annotation.SuppressLint;

import com.vongihealth.live.Live;
import com.vongihealth.network.interactor.UseCase;
import com.wkw.commonbusiness.entity.AbstractQry;
import com.wkw.knowledge.domain.KnowledgeUseCase;
import com.wkw.knowledge.domain.UsersListUseCase;
import com.wkw.uiframework.base.mvp.page.PageEntity;
import com.wkw.uiframework.base.mvp.page.PagePresenterImpl;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by GoGo on 2018-5-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class KnowledgePresenter extends PagePresenterImpl<Integer, String, KonwledgeContract.View> implements KonwledgeContract.Presenter {

    @Inject
    UsersListUseCase mUsersListUseCase;
    @Inject
    KnowledgeUseCase mKnowledgeUseCase;

    @Inject
    public KnowledgePresenter() {
    }

    @Override
    public void resume() {
        super.resume();
        Timber.d("KnowledgePresenter resume");
    }

    @Override
    public void pause() {
        super.pause();
        Timber.d("KnowledgePresenter resume");
    }

    @Override
    public void destroy() {
        super.destroy();
        Timber.d("KnowledgePresenter destroy");
    }

    @SuppressLint("CheckResult")
    @Override
    public void usersList(AbstractQry qry) {
        mUsersListUseCase.execute(qry)
                .subscribe(users -> getView().showDataUserList(users),
                        throwable -> getRxErrorHandler().getHandlerFactory().handleError(throwable));
    }

    @Override
    public UseCase<PageEntity<String>, Integer> providerSource() {
        return mKnowledgeUseCase;
    }
}
