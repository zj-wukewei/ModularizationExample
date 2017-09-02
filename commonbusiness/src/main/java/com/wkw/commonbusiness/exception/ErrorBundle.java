package com.wkw.commonbusiness.exception;

/**
 * Created by wukewei on 2017/8/28.
 */

public interface ErrorBundle {
    Exception getException();
    String getErrorMessage();
}
