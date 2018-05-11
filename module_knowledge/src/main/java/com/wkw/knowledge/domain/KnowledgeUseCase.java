package com.wkw.knowledge.domain;

import com.vongihealth.network.executor.PostExecutionThread;
import com.vongihealth.network.executor.ThreadExecutor;
import com.vongihealth.network.interactor.UseCase;
import com.wkw.knowledge.domain.repository.KnowledgeRepository;
import com.wkw.uiframework.base.mvp.page.PageEntity;
import com.wkw.uiframework.di.PerActivity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by wukewei on 2017/9/12.
 */
@PerActivity
public class KnowledgeUseCase extends UseCase<PageEntity<String>, Integer> {


    private final KnowledgeRepository mKnowledgeRepository;

    @Inject
    public KnowledgeUseCase(KnowledgeRepository knowledgeRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mKnowledgeRepository = knowledgeRepository;

    }

    @Override
    public Observable<PageEntity<String>> buildUseCaseObservable(Integer pn) {
        return mKnowledgeRepository.fetchList(pn);
    }


}
