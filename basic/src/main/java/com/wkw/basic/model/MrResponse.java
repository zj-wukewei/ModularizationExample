package com.wkw.basic.model;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wukewei on 2017/8/25.
 */
@Keep
public class MrResponse<T> {

    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("statusMessage")
    private String statusMessage;


    @SerializedName("data")
    private T data;

    public T getData() {
        return data;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
