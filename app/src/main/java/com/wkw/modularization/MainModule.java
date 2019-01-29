package com.wkw.modularization;

import com.wkw.uiframework.di.PerActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author GoGo on 2019-01-29.
 */
@Module
public abstract class MainModule {

    @PerActivity
    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivityProvider();
}
