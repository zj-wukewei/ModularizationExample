package com.wkw.knowledge.view;

import com.wkw.knowledge.domain.KnowledgeUseCase;
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
    KnowledgeUseCase mKnowledgeUseCase;

    @Inject
    public KnowledgePresenter() {
    }

    @Override
    public Observable<PageEntity<String>> provideSource(Integer pn) {
        return mKnowledgeUseCase.execute(pn);
    }

}
