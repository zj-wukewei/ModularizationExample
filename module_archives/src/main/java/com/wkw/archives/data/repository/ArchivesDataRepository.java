package com.wkw.archives.data.repository;

import com.vongihealth.network.repository.RepositoryUtils;
import com.wkw.archives.data.api.ArchivesApi;
import com.wkw.archives.domain.repository.ArchivesRepository;
import com.wkw.commonbusiness.entity.TokenEntity;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

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

    @Override
    public Observable<String> fetchName() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                try {
                    Thread.sleep(2000);
                    e.onNext("wukwei");
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex);
                }

            }
        });
    }

    @Override
    public Observable<String> fetchPassword() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                try {
                    Thread.sleep(3000);
                    int i = 1 / 0;
                    e.onNext("1234567");
                    e.onComplete();
                } catch (Exception ex) {
                    e.onError(ex);
                }

            }
        });
    }
}
