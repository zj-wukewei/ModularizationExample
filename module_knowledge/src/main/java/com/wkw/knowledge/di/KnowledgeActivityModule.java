package com.wkw.knowledge.di;

import com.wkw.uiframework.di.PerActivity;
import com.wkw.knowledge.KnowledgeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by wukewei on 2017/9/9.
 */
@Module
public abstract class KnowledgeActivityModule {
    @PerActivity
    @ContributesAndroidInjector()
    abstract KnowledgeActivity contributeKnowledgeActivity();
}
