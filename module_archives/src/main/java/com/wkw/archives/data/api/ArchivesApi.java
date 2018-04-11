package com.wkw.archives.data.api;

import com.vongihealth.network.entity.MrResponse;
import com.wkw.commonbusiness.entity.TokenEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wukewei on 2017/9/9.
 */

public interface ArchivesApi {

    @GET("user/v1/login/{user_account}/{user_password}/{user_type}")
    Observable<MrResponse<TokenEntity>> login(@Path("user_account") String mobileNumber, @Path("user_password") String password, @Path("user_type") String type);
}
