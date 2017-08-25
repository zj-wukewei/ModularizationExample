package com.wkw.baisc.exception;

import com.wkw.baisc.model.MrResponse;

/**
 * Created by wukewei on 2017/8/25.
 */

public class ResponseException extends Exception {
    private static final String TAG = "ResponseException";
    public static final int STATUS_CODE_SUCCESS = 0;
    public static final int ERROR_CODE_NEED_LOGIN = -1001;
    public static final int ERROR_CODE_NEED_PERFECT_PROFILE = -1010;
    private final int mStatusCode;
//    private TokenEntity mToken;

    /**
     * @param response 全局响应格式
     */
    public ResponseException(MrResponse response) {
        super(response.getStatusMessage());
        mStatusCode = response.getStatusCode();
        if (mStatusCode == ERROR_CODE_NEED_PERFECT_PROFILE) {
            try {
//                mToken = (TokenEntity) response.getData();
            } catch (ClassCastException e) {
            }
        }
    }

    /**
     * -1	    普通异常，详见 status_msg 字段描述
     * -1001	用户登录凭证不合法，请先登录或重新登录
     * -1010	请前往完善用户信息（昵称、头像）
     * -404	    指定目标不存在或已删除
     *
     * @return 全局响应代码，非0（0为成功）
     */
    public int getStatusCode() {
        return mStatusCode;
    }

//    public TokenEntity getVuser() {
//        return mToken;
//    }
}
