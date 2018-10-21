package com.wkw.commonbusiness;

import com.wkw.commonbusiness.exception.ResponseListenerImpl;
import com.wkw.ext.Ext;
import com.wkw.ext.utils.ConfigManager;
import com.wkw.imageloader.glide.GlideImageLoaderStrategy;
import com.wkw.uiframework.di.AppConfigModule;

import java.util.ArrayList;

import okhttp3.HttpUrl;
import timber.log.Timber;

/**
 * Created by wukewei on 2017/9/9.
 */

public class BaseApplication extends com.wkw.uiframework.app.BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        initExtension();
        ConfigManager.init(this);

    }


    protected AppConfigModule.Builder providerAppConfigModule() {
        AppConfigModule.Builder builder = AppConfigModule.builder();
        builder.baseUrl(HttpUrl.parse("http://192.168.8.164:1001/"))
                .interceptorList(new ArrayList<>())
                .responseErrorListener(new ResponseListenerImpl())
                .imageLoaderStrategy(new GlideImageLoaderStrategy());

        return builder;

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
