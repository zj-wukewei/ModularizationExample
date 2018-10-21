package com.github.wkw.login.di;

import com.github.wkw.login.LoginActivity;
import com.github.wkw.login.provider.UserContentProvider;
import com.wkw.uiframework.di.PerActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by wukewei on 2017/9/9.
 */
@Module
public abstract class LoginModule {
    @PerActivity
    @ContributesAndroidInjector()
    abstract UserContentProvider contributeUserContentProvider();

    @PerActivity
    @ContributesAndroidInjector()
    abstract LoginActivity contributeLoginActivityProvider();
}
