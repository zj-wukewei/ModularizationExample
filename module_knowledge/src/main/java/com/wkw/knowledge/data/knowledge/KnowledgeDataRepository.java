package com.wkw.knowledge.data.knowledge;


import com.vongihealth.network.repository.RepositoryUtils;
import com.wkw.commonbusiness.entity.AbstractQry;
import com.wkw.knowledge.data.KnowledgeApi;
import com.wkw.knowledge.domain.repository.KnowledgeRepository;
import com.wkw.knowledge.entity.User;
import com.wkw.uiframework.base.mvp.page.PageEntity;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by GoGo on 2018-5-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class KnowledgeDataRepository implements KnowledgeRepository {

    @Inject
    KnowledgeApi mApi;

    @Inject
    public KnowledgeDataRepository() {
    }

    @Override
    public Observable<PageEntity<String>> fetchList(Integer pn) {
        PageEntity<String> pageEntity = new PageEntity<>();
        pageEntity.setHasMore(pn != 6);
        pageEntity.setList(new ArrayList<>());
        return Observable.create(new ObservableOnSubscribe<PageEntity<String>>() {
            @Override
            public void subscribe(ObservableEmitter<PageEntity<String>> e) throws Exception {
                try {
                    Thread.sleep(2000);
                    e.onNext(pageEntity);
                    e.onComplete();
                } catch (Exception e1) {
                    e.onError(e1);
                }
            }
        });
    }

    @Override
    public Observable<PageEntity<User>> users(AbstractQry qry) {
        return mApi.users(qry)
                .compose(RepositoryUtils.handleResult());
    }
}
