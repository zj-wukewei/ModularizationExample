package com.wkw.archives.data.repository;

import com.wkw.archives.data.api.ArchivesApi;
import com.wkw.archives.domain.entity.ArchivesEntity;
import com.wkw.archives.domain.repository.ArchivesRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by wukewei on 2017/9/12.
 */

public class ArchivesDataRepository implements ArchivesRepository {

    @Inject
    ArchivesApi mArchivesApi;

    @Inject
    public ArchivesDataRepository() {
    }

    @Override
    public Observable<ArchivesEntity> archivesList() {
        return null;
    }
}
