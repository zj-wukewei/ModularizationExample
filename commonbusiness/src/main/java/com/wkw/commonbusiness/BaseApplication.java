package com.wkw.commonbusiness;

import android.app.Application;

import com.vongihealth.network.NetWorkManager;
import com.wkw.basic.network.HeadInterceptor;
import com.wkw.ext.Ext;
import com.wkw.ext.utils.ConfigManager;

import timber.log.Timber;

/**
 * Created by wukewei on 2017/9/9.
 */

public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        initExtension();

        ConfigManager.init(this);

        initNetWork();
    }

    private void initNetWork() {
        NetWorkManager.init("http://192.168.3.19:8091/big-shell-app/", this);
        NetWorkManager.isDebug(true);
        NetWorkManager.addInterceptors(new HeadInterceptor());
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

        @Override
        public void handleLoginOut() {
            Timber.d("handleLoginOut");
        }
    }
}
