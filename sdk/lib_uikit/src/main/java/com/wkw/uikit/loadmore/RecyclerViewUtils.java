

package com.wkw.uikit.loadmore;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

/**
 * 设置rv manager 类型
 *
 * @author cjj
 */
class RecyclerViewUtils {

    private static final String TAG = "RecyclerViewUtils";

    public static void setVerticalLinearLayout(RecyclerView rv) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(rv.getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    Log.e(TAG, "meet an IndexOutOfBoundsException in RecyclerView");
                }
            }
        };
        rv.setLayoutManager(layoutManager);
    }

    public static void setGridLayout(final RecyclerView rv, int spanCount) {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(rv.getContext(), spanCount, GridLayoutManager.VERTICAL, false) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    Log.e(TAG, "meet an IndexOutOfBoundsException in RecyclerView");
                }
            }
        };
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = rv.getAdapter().getItemViewType(position);
                if (viewType < 0) {
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
            }
        });
        rv.setLayoutManager(gridLayoutManager);
    }

    public static void setStaggeredGridLayoutManager(RecyclerView rv, int spanCount) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL) {
            @Override
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    Log.e(RecyclerViewUtils.TAG, "meet an IndexOutOfBoundsException in RecyclerView");
                }
            }
        };
        rv.setLayoutManager(staggeredGridLayoutManager);
    }
}
