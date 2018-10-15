package com.wkw.knowledge.data;

import com.vongihealth.network.entity.MrResponse;
import com.wkw.commonbusiness.entity.AbstractQry;
import com.wkw.knowledge.entity.User;
import com.wkw.uiframework.base.mvp.page.PageEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by wukewei on 2017/9/12.
 */

public interface KnowledgeApi {
    @POST("user/users")
    Observable<MrResponse<PageEntity<User>>> users(@Body AbstractQry qry);
}
