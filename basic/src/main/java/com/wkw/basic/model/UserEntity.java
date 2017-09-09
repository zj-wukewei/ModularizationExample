package com.wkw.basic.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wukewei on 2017/9/2.
 */

public class UserEntity {

    @SerializedName("id")
    private int userId;

    @SerializedName("full_name")
    private String fullname;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
