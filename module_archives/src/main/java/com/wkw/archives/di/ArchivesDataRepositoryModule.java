package com.wkw.archives.di;

import com.vongihealth.network.retrofit.MrService;
import com.wkw.archives.data.api.ArchivesApi;
import com.wkw.archives.data.repository.ArchivesDataRepository;
import com.wkw.archives.domain.repository.ArchivesRepository;

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

    @Provides
    @Singleton
    ArchivesRepository prvidesArchivesRepository(ArchivesDataRepository archivesDataRepository) {
        return archivesDataRepository;
    }

}
