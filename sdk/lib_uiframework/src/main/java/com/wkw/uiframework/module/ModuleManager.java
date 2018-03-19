package com.wkw.uiframework.module;

/**
 * Created by GoGo on 2018-3-19.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class ModuleManager {
    private static ModuleManager instance = new ModuleManager();

    public static ModuleManager g() {
        return instance;
    }

    public void addModule(Module<?, ?> module) {
    }

    public Module<?, ?> getModule(String moduleName) {
        return null;
    }

    private ModuleManager() {
    }

    public static Object LoadModule(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Object result = null;
        Class<?> c = Class.forName(className);
        if (c != null) {
            result = c.newInstance();
        }
        return result;
    }
}
