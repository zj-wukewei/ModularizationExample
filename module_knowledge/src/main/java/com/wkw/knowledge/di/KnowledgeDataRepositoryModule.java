package com.wkw.knowledge.di;

import com.vongihealth.network.retrofit.MrService;
import com.wkw.knowledge.data.KnowledgeApi;
import com.wkw.knowledge.data.knowledge.KnowledgeDataRepository;
import com.wkw.knowledge.domain.repository.KnowledgeRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wukewei on 2017/9/12.
 */
@Module
public class KnowledgeDataRepositoryModule {

    @Provides
    @Singleton
    KnowledgeApi providesAKnowledgeApi(MrService mrService) {
        return mrService.createApi(KnowledgeApi.class);
    }

    @Provides
    @Singleton
    KnowledgeRepository prvidesKnowledgeRepository(KnowledgeDataRepository knowledgeRepository) {
        return knowledgeRepository;
    }
}
