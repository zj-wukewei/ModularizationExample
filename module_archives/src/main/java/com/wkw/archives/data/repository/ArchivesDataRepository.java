package com.wkw.archives.data.repository;

import com.vongihealth.network.repository.RepositoryUtils;
import com.wkw.archives.data.api.ArchivesApi;
import com.wkw.archives.domain.repository.ArchivesRepository;
import com.wkw.commonbusiness.entity.TokenEntity;

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
    public Observable<TokenEntity> archivesList() {
        return mArchivesApi.login("1825800578", "123456", "1")
                .compose(RepositoryUtils.handleResult());
    }
}
