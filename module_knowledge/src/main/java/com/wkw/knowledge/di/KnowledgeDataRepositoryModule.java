package com.wkw.knowledge.di;

import com.wkw.basic.network.MrService;
import com.wkw.knowledge.data.KnowledgeApi;

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
}
