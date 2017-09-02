package com.wkw.commonbusiness.exception;

/**
 * Created by wukewei on 2017/8/28.
 */

public class DefaultErrorBundle implements ErrorBundle {

    private static final String DEFAULT_ERROE_MSG = "未知错误";

    private final Exception exception;

    public DefaultErrorBundle(Exception exception) {
        this.exception = exception;
    }


    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        return (exception != null) ? exception.getMessage() : DEFAULT_ERROE_MSG;
    }
}
