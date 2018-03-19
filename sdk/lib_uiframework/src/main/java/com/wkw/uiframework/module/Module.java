package com.wkw.uiframework.module;

/**
 * Created by GoGo on 2018-3-19.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public abstract class Module<T, C> implements IProxy<T, C> {
    public abstract String getName();
    public abstract int getVersion();

    public Module() {
        ModuleManager.g().addModule(this);
    }
}
