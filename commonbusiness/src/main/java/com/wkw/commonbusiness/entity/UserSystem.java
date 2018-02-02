package com.wkw.commonbusiness.entity;

import com.wkw.ext.utils.ConfigManager;
import com.wkw.ext.utils.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by wukewei on 2017/8/25.
 */

@Singleton
public class UserSystem {
    private static final String KEY_TOKEN = "token";
    private static final String KEY_USER_ID = "uid";

    private String mToken;
    private String mUserId;

    @Inject
    public UserSystem() {
    }

    public String getToken() {
        if (StringUtils.isEmpty(mToken)) {
            String token = ConfigManager.getString(KEY_TOKEN, "", ConfigManager.KEY_ACCOUNT);
            String userId = ConfigManager.getString(KEY_USER_ID, "", ConfigManager.KEY_ACCOUNT);
            setTokenWrapper(new TokenEntity(userId, token));
        }
        return mToken;
    }

    public void setToken(String token) {
        if (StringUtils.equal(token, mUserId)) {
            return;
        }
        ConfigManager.putString(KEY_TOKEN, token, ConfigManager.KEY_ACCOUNT);
        this.mToken = token;
    }

    public String getUserId() {
        if (StringUtils.isEmpty(mUserId)) {
            String token = ConfigManager.getString(KEY_TOKEN, "", ConfigManager.KEY_ACCOUNT);
            String userId = ConfigManager.getString(KEY_USER_ID, "", ConfigManager.KEY_ACCOUNT);
            setTokenWrapper(new TokenEntity(userId, token));
        }
        return mUserId;
    }

    public void setUserId(String userId) {
        if (StringUtils.equal(userId, mUserId)) {
            return;
        }
        ConfigManager.putString(KEY_USER_ID, userId, ConfigManager.KEY_ACCOUNT);
        this.mUserId = userId;
    }

    public void setTokenWrapper(TokenEntity token) {
        if (token != null) {
            setToken(token.getToken());
            setUserId(token.getUid());
        }
    }
}
