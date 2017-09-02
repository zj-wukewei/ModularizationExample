package com.wkw.commonbusiness.module;

/**
 * Created by wukewei on 2017/8/27.
 */

public abstract class Module<T, C> implements IProxy<T, C> {
    public abstract String getName();
    public abstract int getVersion();

    public Module() {
        ModuleManager.g().addModule(this);
    }
}
