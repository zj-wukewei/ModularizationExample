package com.wkw.uiframework.module;

/**
 * Created by GoGo on 2018-3-19.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public interface IProxy<T, C> {
    T getUiInterface();
    C getServiceInterface();
}
