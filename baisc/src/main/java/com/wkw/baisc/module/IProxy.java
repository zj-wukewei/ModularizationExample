package com.wkw.baisc.module;

/**
 * Created by wukewei on 2017/8/27.
 */

public interface IProxy<T, C> {
    T getUiInterface();
    C getServiceInterface();
}
