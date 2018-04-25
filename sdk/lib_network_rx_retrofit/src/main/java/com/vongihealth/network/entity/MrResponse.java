package com.vongihealth.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GoGo on 2018-4-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class MrResponse<T> {
    @SerializedName("code")
    private int statusCode;

    @SerializedName("msg")
    private String statusMessage;


    @SerializedName("data")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean hasMore() {
        return true;
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

    public MrResponse() {
        this(0, "success", null);
    }

    public MrResponse(int statusCode, String statusMessage, T data) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.data = data;
    }
}
