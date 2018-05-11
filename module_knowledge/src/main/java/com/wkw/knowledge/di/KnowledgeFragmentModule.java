package com.wkw.knowledge.di;

import com.wkw.knowledge.view.fragment.KnowledgeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by GoGo on 2018-5-11.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
@Module
public abstract class KnowledgeFragmentModule {
    @ContributesAndroidInjector
    abstract KnowledgeFragment contributeKnowledgeFragment();
}
