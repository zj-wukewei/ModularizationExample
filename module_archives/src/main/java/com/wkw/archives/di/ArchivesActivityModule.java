package com.wkw.archives.di;

import com.wkw.archives.view.ArchivesActivity;
import com.wkw.commonbusiness.di.PerActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by wukewei on 2017/9/9.
 */
@Module
public abstract class ArchivesActivityModule {
    @PerActivity
    @ContributesAndroidInjector()
    abstract ArchivesActivity contributeArchivesActivity();
}
