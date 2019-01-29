package com.wkw.commonbusiness.entity;

import com.wkw.commonbusiness.utils.LoginModuleUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by wukewei on 2017/8/25.
 */

@Singleton
public class UserSystem {
    private static final String KEY_TOKEN = "token";
    private static final String KEY_USER_ID = "uid";

    private TokenEntity mTokenEntity;

    @Inject
    public UserSystem() {
        LoginModuleUtils.getInstance().registerObserver(entity -> {
            this.mTokenEntity = entity;
        });
    }


    public TokenEntity getTokenEntity() {
        return mTokenEntity;
    }

    public void setmTokenEntity(TokenEntity mTokenEntity) {
        this.mTokenEntity = mTokenEntity;
    }
}
