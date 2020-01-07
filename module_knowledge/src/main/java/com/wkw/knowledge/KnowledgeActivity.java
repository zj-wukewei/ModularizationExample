package com.wkw.knowledge;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wkw.knowledge.entity.User;
import com.wkw.knowledge.view.FragmentPagerFragment;
import com.wkw.knowledge.view.KnowledgePresenter;
import com.wkw.knowledge.view.KonwledgeContract;
import com.wkw.knowledge.view.fragment.KnowledgeFragment;
import com.wkw.uiframework.base.mvp.MvpActivity;
import com.wkw.uiframework.base.mvp.page.PageEntity;
import com.wkw.uikit.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.OnFlingOverListener;
import ru.noties.scrollable.ScrollableLayout;
import timber.log.Timber;

/**
 * Created by wukewei on 2017/9/9.
 */

public class KnowledgeActivity extends MvpActivity<KonwledgeContract.View, KonwledgeContract.Presenter> implements KonwledgeContract.View, HasSupportFragmentInjector {

    private static final String TAG = "KnowledgeActivity";

    @Inject
    KnowledgePresenter mKnowledgePresenter;

    @Override
    protected KonwledgeContract.Presenter getPresenter() {
        return mKnowledgePresenter;
    }
    private SlidingTabLayout mSlidingTabLayout;
    private ScrollableLayout mScrollableLayout;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_activity_knowledge);
        mSlidingTabLayout = findViewById(R.id.sliding_tab_layout);
        mScrollableLayout = findViewById(R.id.scrollable_layout);
        mViewPager = findViewById(R.id.view_pager);

        String[] header = {"推荐医生" , "同城"};
        ArrayList<Fragment> fragments = new ArrayList<>(2);
        fragments.add(KnowledgeFragment.newInstance());
        fragments.add(KnowledgeFragment.newInstance());
        mSlidingTabLayout.setViewPager(mViewPager, header, this, fragments);

        mScrollableLayout.setDraggableView(mSlidingTabLayout);

        final CurrentFragment currentFragment = new CurrentFragmentImpl(mViewPager, getSupportFragmentManager());

        mScrollableLayout.setCanScrollVerticallyDelegate(new CanScrollVerticallyDelegate() {
            @Override
            public boolean canScrollVertically(int direction) {
                final FragmentPagerFragment fragment = currentFragment.currentFragment();
                return fragment != null && fragment.canScrollVertically(direction);
            }
        });

        mScrollableLayout.setOnFlingOverListener(new OnFlingOverListener() {
            @Override
            public void onFlingOver(int y, long duration) {
                final FragmentPagerFragment fragment = currentFragment.currentFragment();
                if (fragment != null) {
                    fragment.onFlingOver(y, duration);
                }
            }
        });
    }


    @Override
    public void showLoading() {
        Timber.d("showLoading");
    }

    @Override
    public void hideLoading() {
        Timber.d("hideLoading");
    }

    @Override
    public void showError(Exception e) {
        Timber.d("Exception");
    }

    @Override
    public void showData(PageEntity<String> data) {
        Timber.d(data.toString());
        Timber.d(data.isHasMore() + "");
        Timber.d(data.getList().size() + "");
    }

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }

    @Override
    public void showDataUserList(PageEntity<User> users) {
        Timber.d(users.toString());
    }






    private static class CurrentFragmentImpl implements CurrentFragment {

        private final ViewPager mViewPager;
        private final FragmentManager mFragmentManager;
        private final FragmentPagerAdapter mAdapter;

        CurrentFragmentImpl(ViewPager pager, FragmentManager manager) {
            mViewPager = pager;
            mFragmentManager = manager;
            mAdapter = (FragmentPagerAdapter) pager.getAdapter();
        }

        @Override
        @Nullable
        public FragmentPagerFragment currentFragment() {
            final FragmentPagerFragment out;
            final int position = mViewPager.getCurrentItem();
            if (position < 0
                    || position >= mAdapter.getCount()) {
                out = null;
            } else {
                final String tag = makeFragmentName(mViewPager.getId(), mAdapter.getItemId(position));
                final Fragment fragment = mFragmentManager.findFragmentByTag(tag);
                if (fragment != null) {
                    out = (FragmentPagerFragment) fragment;
                } else {
                    // fragment is still not attached
                    out = null;
                }
            }
            return out;
        }

        // this is really a bad thing from Google. One cannot possible obtain normally
        // an instance of a fragment that is attached. Bad, really bad
        private static String makeFragmentName(int viewId, long id) {
            return "android:switcher:" + viewId + ":" + id;
        }
    }



    private interface CurrentFragment {
        @Nullable
        FragmentPagerFragment currentFragment();
    }
}
