package com.wkw.commonbusiness.module;

import android.text.TextUtils;

import com.wkw.sdk.utils.Logger;

/**
 * Created by wukewei on 2017/8/27.
 */

public abstract class Proxy<T, C> implements IProxy<T, C> {
    private static final String TAG = "Proxy";

    private Module<T, C> proxy;

    @Override
    public final T getUiInterface() {
        return getProxy().getUiInterface();
    }

    @Override
    public final C getServiceInterface() {
        return getProxy().getServiceInterface();
    }

    public abstract String getModuleClassName();

    public abstract Module<T, C> getDefaultModule();

    protected Module<T, C> getProxy() {
        if (proxy == null) {
            String module = getModuleClassName();
            if (!TextUtils.isEmpty(module)) {
                try {
                    proxy = (Module<T, C>) ModuleManager.LoadModule(module);
                } catch (Throwable e) {
                    Logger.e(TAG, module + " module load failed" + e.getMessage());
                    proxy = getDefaultModule();
                }
            }
        }
        return proxy;
    }
}
