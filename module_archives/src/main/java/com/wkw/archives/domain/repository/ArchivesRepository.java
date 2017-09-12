package com.wkw.archives.domain.repository;

import com.wkw.archives.domain.entity.ArchivesEntity;

import io.reactivex.Observable;

/**
 * Created by wukewei on 2017/9/12.
 */

public interface ArchivesRepository {
    Observable<ArchivesEntity> archivesList();
}
