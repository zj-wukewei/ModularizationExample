package com.wkw.knowledge.view;

import android.annotation.SuppressLint;

import com.vongihealth.live.Live;
import com.wkw.commonbusiness.entity.AbstractQry;
import com.wkw.knowledge.domain.KnowledgeUseCase;
import com.wkw.knowledge.domain.UsersListUseCase;
import com.wkw.uiframework.base.mvp.page.PageEntity;
import com.wkw.uiframework.base.mvp.page.PagePresenterImpl;

import javax.inject.Inject;

import io.reactivex.Observable;

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
    public Observable<PageEntity<String>> provideSource(Integer pn) {
        return mKnowledgeUseCase.execute(pn);
    }

    @SuppressLint("CheckResult")
    @Override
    public void usersList(AbstractQry qry) {
        mUsersListUseCase.execute(qry)
                .compose(Live.bindLifecycle(getLifecycleOwner()))
                .subscribe(users -> getView().showDataUserList(users),
                        throwable -> getRxErrorHandler().getHandlerFactory().handleError(throwable));
    }
}
