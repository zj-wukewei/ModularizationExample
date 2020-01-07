package com.wkw.uiframework.base.mvp.page;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wkw.ext.utils.ListUtils;
import com.wkw.ext.utils.NetWorkUtils;
import com.wkw.uiframework.R;
import com.wkw.uiframework.adapter.MultiTypeAdapter;
import com.wkw.uiframework.base.mvp.MvpFragment;
import com.wkw.uiframework.constants.PageConstants;
import com.wkw.uiframework.databinding.FragmentListBinding;
import com.wkw.uiframework.error.ErrorMessageFactory;
import com.wkw.uikit.ProgressLayout;
import com.wkw.uikit.loadmore.OnLoadMoreListener;
import com.wkw.uikit.loadmore.RecyclerViewWithFooter;

/**
 * Created by GoGo on 2018-5-10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public abstract class PageFragment<Request, Response, V extends PageView<Response>, P extends PagePresenter<Request, Response, V>> extends MvpFragment<V, P> implements PageView<Response> {

    public int mCurrentPage = PageConstants.FIRST_PAGE;
    public boolean mIsFetching = false;
    public boolean isInit = true;
    private MultiTypeAdapter mMultiTypeAdapter;

    private FragmentListBinding mBinding;
    private View mFragmentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mFragmentView == null) {

            mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
            mFragmentView = mBinding.getRoot();
            initHeaderAndFooter();
        } else {
            ViewGroup parent = (ViewGroup) mFragmentView.getParent();
            if (parent != null) {
                parent.removeView(mFragmentView);
            }
        }
        return mBinding.getRoot();
    }

    protected SwipeRefreshLayout.OnRefreshListener onRefreshListener = () -> {
        mCurrentPage = PageConstants.FIRST_PAGE;
        getPresenter().fetchData(provideRequest());
    };

    public void clickNoData() {
        getPresenter().fetchData(provideRequest());
    }

    public OnLoadMoreListener mLoadMoreListener = () -> {
        mCurrentPage++;
        getPresenter().fetchData(provideRequest());
    };

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 需要子类手动调用
     */
    protected void initHeaderAndFooter() {
        getSwipeRefreshLayout().setOnRefreshListener(onRefreshListener);
        getSwipeRefreshLayout().setColorSchemeResources(R.color.refresh_progress);
        getRecyclerViewWithFooter().setOnLoadMoreListener(mLoadMoreListener);
        getRecyclerViewWithFooter().setPullToLoad();
        mMultiTypeAdapter = new MultiTypeAdapter(getContext());
        initAdapter(mMultiTypeAdapter);
        getRecyclerViewWithFooter().setAdapter(mMultiTypeAdapter);
        getRecyclerViewWithFooter().setVerticalLinearLayout();
    }

    protected abstract void initAdapter(MultiTypeAdapter adapter);


    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return mBinding.swipeRefreshLayout;
    }

    protected RecyclerViewWithFooter getRecyclerViewWithFooter() {
        return mBinding.rvWithFooter;
    }

    protected ProgressLayout getProgressLayout() {
        return mBinding.progressLayout;
    }

    public abstract Request provideRequest();

    @Override
    public void showData(PageEntity<Response> data) {
        if (data == null) return;

        if (data.isHasMore()) {
            getRecyclerViewWithFooter().setPullToLoad();
        } else {
            getRecyclerViewWithFooter().setEnd();
        }

        if (ListUtils.isNotEmpty(data.getList())) {
            if (isLoadMore()) {
                mMultiTypeAdapter.addAll(data.getList(), provideMultiViewTyper());
            } else {
                mMultiTypeAdapter.set(data.getList(), provideMultiViewTyper());
            }
        }
    }

    protected abstract MultiTypeAdapter.MultiViewTyper provideMultiViewTyper();

    protected boolean isLoadMore() {
        return mCurrentPage > PageConstants.FIRST_PAGE;
    }

    @Override
    public void showLoading() {
        mIsFetching = true;
        if (mCurrentPage == PageConstants.FIRST_PAGE && isInit) {
            if (getProgressLayout() != null) {
                getProgressLayout().showLoading();
            }
        }
        isInit = false;
    }

    protected void setRefreshing(boolean refreshing) {
        if (getSwipeRefreshLayout() != null) {
            if (refreshing) {
                getSwipeRefreshLayout().setRefreshing(true);
            } else {
                getSwipeRefreshLayout().postDelayed(() -> getSwipeRefreshLayout().setRefreshing(false), 800);
            }
        }
    }

    protected void loadEmpty() {
        if (getProgressLayout() != null) {
            getProgressLayout().showNone(v -> clickNoData());
        }
        if (mCurrentPage == PageConstants.FIRST_PAGE) {
            setRefreshing(false);
        }
        mIsFetching = false;
    }

    @Override
    public void hideLoading() {
        RecyclerView.Adapter adapter = getRecyclerViewWithFooter().getAdapter();
        if (mCurrentPage == PageConstants.FIRST_PAGE &&
                getRecyclerViewWithFooter() != null &&
                adapter != null &&
                (adapter.getItemCount() == 0
                        || (adapter.getItemCount() == 1 &&
                        (adapter.getItemViewType(0) == RecyclerViewWithFooter.
                                LoadMoreAdapter.LOAD_MORE_VIEW_TYPE ||
                                adapter.getItemViewType(0) == RecyclerViewWithFooter.
                                        LoadMoreAdapter.EMPTY_VIEW_TYPE)))) {
            isInit = true;
            loadEmpty();
            return;
        }
        if (getProgressLayout() != null) {
            getProgressLayout().showContent();
        }
        if (mCurrentPage == PageConstants.FIRST_PAGE) {
            setRefreshing(false);
        }
        mIsFetching = false;
    }

    public void setFailedText(CharSequence text) {
        getProgressLayout().setFailedText(text);
    }

    @Override
    public void showError(Exception e) {
        mIsFetching = false;
        if (mCurrentPage == PageConstants.FIRST_PAGE && getProgressLayout() != null) {
            isInit = true;
            final View.OnClickListener onClickListener = v -> getPresenter().fetchData(provideRequest());
            if (NetWorkUtils.isNetworkConnected()) {
                String errorMessage = ErrorMessageFactory.create(getContext(), e);
                setFailedText(errorMessage);
                getProgressLayout().showFailed(onClickListener);
            } else {
                getProgressLayout().showNetError(onClickListener);
            }
        } else {
            getRecyclerViewWithFooter().setFailure(v -> {
                mCurrentPage--;
                getPresenter().fetchData(provideRequest());
            });
        }
        setRefreshing(false);
    }
}
