package com.wkw.knowledge.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.wkw.knowledge.R;
import com.wkw.knowledge.domain.UsersListUseCase;
import com.wkw.knowledge.entity.User;
import com.wkw.knowledge.view.FragmentPagerFragment;
import com.wkw.knowledge.view.KnowledgePresenter;
import com.wkw.knowledge.view.KonwledgeContract;
import com.wkw.uiframework.adapter.MultiTypeAdapter;
import com.wkw.uiframework.base.mvp.page.PageEntity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import timber.log.Timber;

/**
 * Created by GoGo on 2018-5-11.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class KnowledgeFragment extends FragmentPagerFragment<Integer, String, KonwledgeContract.View, KonwledgeContract.Presenter> implements KonwledgeContract.View {

    public static final int TYPE_VIEW = 1;


    public static KnowledgeFragment newInstance() {
        return new KnowledgeFragment();
    }

    @Inject
    KnowledgePresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected KonwledgeContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresenter().fetchData(provideRequest());
        getPresenter().usersList(UsersListUseCase.create(1));
    }

    @Override
    protected void initAdapter(MultiTypeAdapter adapter) {
        adapter.addViewTypeToLayoutMap(TYPE_VIEW, R.layout.knowledge_item_string);
    }

    @Override
    public Integer provideRequest() {
        return mCurrentPage;
    }

    @Override
    protected MultiTypeAdapter.MultiViewTyper provideMultiViewTyper() {
        return o -> TYPE_VIEW;
    }

    @Override
    public void showDataUserList(PageEntity<User> users) {
        Timber.d(users.toString());
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return getRecyclerViewWithFooter() != null && getRecyclerViewWithFooter().canScrollVertically(direction);
    }

    @Override
    public void onFlingOver(int y, long duration) {
        if (getRecyclerViewWithFooter() != null) {
            getRecyclerViewWithFooter().smoothScrollBy(0, y);
        }
    }
}
