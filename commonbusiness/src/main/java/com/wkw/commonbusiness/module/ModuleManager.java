package com.wkw.commonbusiness.module;

/**
 * Created by wukewei on 2017/8/27.
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
