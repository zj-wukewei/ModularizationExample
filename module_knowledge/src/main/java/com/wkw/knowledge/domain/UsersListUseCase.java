package com.wkw.knowledge.domain;

import com.vongihealth.network.executor.PostExecutionThread;
import com.vongihealth.network.executor.ThreadExecutor;
import com.vongihealth.network.interactor.UseCase;
import com.wkw.commonbusiness.entity.AbstractQry;
import com.wkw.knowledge.domain.repository.KnowledgeRepository;
import com.wkw.knowledge.entity.User;
import com.wkw.uiframework.base.mvp.page.PageEntity;
import com.wkw.uiframework.di.PerActivity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by wukewei on 2017/9/12.
 */
@PerActivity
public class UsersListUseCase extends UseCase<PageEntity<User>, AbstractQry> {


    private final KnowledgeRepository mKnowledgeRepository;

    @Inject
    public UsersListUseCase(KnowledgeRepository knowledgeRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mKnowledgeRepository = knowledgeRepository;

    }

    @Override
    public Observable<PageEntity<User>> buildUseCaseObservable(AbstractQry qry) {
        return mKnowledgeRepository.users(qry);
    }


    public static AbstractQry create(int pn) {
        return new AbstractQry(pn);
    }
}
