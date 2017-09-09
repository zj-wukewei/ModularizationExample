package com.wkw.basic.cache;

import com.wkw.basic.model.UserEntity;

import io.reactivex.Observable;

/**
 * Created by wukewei on 2017/9/2.
 */

public interface UserCache {

    Observable<UserEntity> get(final int userId);


    void put(UserEntity userEntity);
}
