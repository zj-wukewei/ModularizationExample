package com.wkw.commonbusiness;

import android.app.Application;

import com.wkw.sdk.Ext;
import com.wkw.sdk.utils.ConfigManager;

/**
 * Created by wukewei on 2017/9/9.
 */

public class BaseApplication extends Application  {


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
            return false;
        }
    }
}
