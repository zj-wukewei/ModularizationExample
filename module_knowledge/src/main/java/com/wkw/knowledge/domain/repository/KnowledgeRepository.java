package com.wkw.knowledge.domain.repository;


import com.wkw.commonbusiness.entity.AbstractQry;
import com.wkw.knowledge.entity.User;
import com.wkw.uiframework.base.mvp.page.PageEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;

/**
 * Created by GoGo on 2018-5-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public interface KnowledgeRepository {
    Observable<PageEntity<String>> fetchList(Integer pn);

    Observable<PageEntity<User>> users(@Body AbstractQry qry);
}
