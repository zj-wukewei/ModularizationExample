package com.wkw.archives.data.repository;

import com.vongihealth.network.entity.MrResponse;
import com.vongihealth.network.exception.ResponseException;
import com.vongihealth.network.repository.RepositoryUtils;
import com.wkw.archives.data.api.ArchivesApi;
import com.wkw.archives.domain.repository.ArchivesRepository;
import com.wkw.commonbusiness.entity.TokenEntity;
import com.wkw.ext.utils.StringUtils;

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
        return Observable.create(new ObservableOnSubscribe<TokenEntity>() {
            @Override
            public void subscribe(ObservableEmitter<TokenEntity> e) throws Exception {
                Thread.sleep(4000);
                e.onNext(new TokenEntity("1111", "222222"));
                e.onComplete();
            }
        });
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
    public Observable<String> fetchPassword(String param) {
        return Observable.create((ObservableOnSubscribe<TokenEntity>) e -> {
            Thread.sleep(3000);
            e.onNext(new TokenEntity("我有token了", null));
        }).flatMap(entity -> {
            if (!StringUtils.isEmpty(param)) {
                return Observable.just(entity.getUid());
            }
            return Observable.error(new ResponseException(new MrResponse(ResponseException.ERROR_CODE_NEED_LOGIN, "重新登录", null)));
        });
    }
}
