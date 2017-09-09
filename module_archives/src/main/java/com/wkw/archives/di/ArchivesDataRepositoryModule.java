package com.wkw.archives.di;

import com.wkw.archives.data.ArchivesApi;
import com.wkw.basic.network.MrService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wukewei on 2017/9/9.
 */

@Module
public class ArchivesDataRepositoryModule {

    @Provides
    @Singleton
    ArchivesApi providesArchivesApi(MrService mrService) {
        return mrService.createApi(ArchivesApi.class);
    }
}
