package com.wkw.archives.domain.repository;

import com.wkw.commonbusiness.entity.TokenEntity;

import io.reactivex.Observable;

/**
 * Created by wukewei on 2017/9/12.
 */

public interface ArchivesRepository {
    Observable<TokenEntity> archivesList();

    Observable<String> fetchName();

    Observable<String> fetchPassword();
}
