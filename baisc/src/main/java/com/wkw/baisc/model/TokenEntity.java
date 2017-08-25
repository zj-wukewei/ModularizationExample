package com.wkw.baisc.model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wukewei on 2017/8/25.
 */

@Keep
public class TokenEntity {

    @SerializedName("token")
    private String token;
    @SerializedName("userId")
    private String uid;


    public TokenEntity(String uid, String token) {
        this.token = token;
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public String getUid() {
        return uid;
    }


    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenEntity{" +
                "token='" + token + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
