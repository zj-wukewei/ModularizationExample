package com.wkw.commonbusiness;

import com.wkw.ext.Ext;
import com.wkw.ext.utils.ConfigManager;

import timber.log.Timber;

/**
 * Created by wukewei on 2017/9/9.
 */

public class BaseApplication extends com.wkw.uiframework.app.BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        initExtension();
        ConfigManager.init(this);
    }


    private void initExtension() {
        Ext.init(this, new ExtImpl());
    }


    static class ExtImpl extends Ext {

        @Override
        public String getCurOpenId() {
            return null;
        }

        @Override
        public int getScreenHeight() {
            return 0;
        }

        @Override
        public int getScreenWidth() {
            return 0;
        }

        @Override
        public boolean isAvailable() {
            return false;
        }

        @Override
        public boolean isDebuggable() {
            return true;
        }

        @Override
        public void handleLoginOut() {
            Timber.d("handleLoginOut");
        }
    }
}
