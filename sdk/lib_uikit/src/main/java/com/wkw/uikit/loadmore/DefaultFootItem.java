

package com.wkw.uikit.loadmore;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wkw.uikit.R;


/**
 * @author cjj
 */
public class DefaultFootItem extends FootItem {

    private static final String TAG = "DefaultFootItem";

    private ProgressBar mProgressBar;
    private TextView mLoadingText;
    private TextView mPullToLoadText;
    private TextView mEndTextView;
    private TextView mFailureTextView;

    @Override
    public View onCreateView(ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rv_with_footer_loading, parent, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.rv_with_footer_loading_progress);
        mEndTextView = (TextView) view.findViewById(R.id.rv_with_footer_loading_end);
        mLoadingText = (TextView) view.findViewById(R.id.rv_with_footer_loading_load);
        mPullToLoadText = (TextView) view.findViewById(R.id.rv_with_footer_loading_pull_to_load);
        mFailureTextView = (TextView) view.findViewById(R.id.rv_with_footer_failure);
        return view;
    }

    @Override
    public void onBindData(View view, int state) {
        if (state == RecyclerViewWithFooter.STATE_LOADING) {
            if (TextUtils.isEmpty(loadingText)) {
                showProgressBar(view.getContext().getResources().getString(R.string.rv_with_footer_loading));
            } else {
                showProgressBar(loadingText);
            }
        } else if (state == RecyclerViewWithFooter.STATE_END) {
            if (TextUtils.isEmpty(endText)) {
                showPullToLoad(view.getContext().getResources().getString(R.string.rv_with_footer_empty));
            } else {
                showPullToLoad(endText);
            }
        } else if (state == RecyclerViewWithFooter.STATE_PULL_TO_LOAD) {
            if (TextUtils.isEmpty(pullToLoadText)) {
                showPullToLoad(view.getContext().getResources().getString(R.string.rv_with_footer_pull_load_more));
            } else {
                showPullToLoad(pullToLoadText);
            }
        } else if (state == RecyclerViewWithFooter.STATE_FAILURE) {
            if (TextUtils.isEmpty(failureText)) {
                showFailure(view.getContext().getResources().getString(R.string.rv_with_footer_failure));
            } else {
                showFailure(failureText);
            }
        }
    }

    public void showFailure(CharSequence message) {
        if (mFailureTextView != null) {
            mFailureTextView.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(message)) {
                mFailureTextView.setText(message);
                mFailureTextView.setOnClickListener(v -> {
                    showProgressBar(v.getContext().getResources().getString(R.string.rv_with_footer_loading));
                    if (mRetryClick != null) {
                        mRetryClick.onClick(v);
                    }
                });
            }
        }
        if (mEndTextView != null) {
            mEndTextView.setVisibility(View.GONE);
        }
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
        if (mLoadingText != null) {
            mLoadingText.setVisibility(View.GONE);
        }

        if (mEndTextView != null) {
            mEndTextView.setVisibility(View.GONE);
        }
    }

    public void showPullToLoad(CharSequence message) {
        if (mPullToLoadText != null) {
            mPullToLoadText.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(message)) {
                mPullToLoadText.setText(message);
            }
        }
        if (mEndTextView != null) {
            mEndTextView.setVisibility(View.GONE);
        }
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
        if (mLoadingText != null) {
            mLoadingText.setVisibility(View.GONE);
        }
        if (mFailureTextView != null) {
            mFailureTextView.setVisibility(View.GONE);
        }
    }

    public void showProgressBar(CharSequence load) {
        if (mEndTextView != null) {
            mEndTextView.setVisibility(View.GONE);
        }
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
        if (mPullToLoadText != null) {
            mPullToLoadText.setVisibility(View.GONE);
        }
        if (mLoadingText != null) {
            if (!TextUtils.isEmpty(load)) {
                mLoadingText.setVisibility(View.VISIBLE);
                mLoadingText.setText(load);
            } else {
                mLoadingText.setVisibility(View.GONE);
            }
        }
        if (mFailureTextView != null) {
            mFailureTextView.setVisibility(View.GONE);
        }
    }

    public void showEnd(CharSequence end) {
        if (mEndTextView != null) {
            mEndTextView.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(end)) {
                mEndTextView.setText(end);
            }
        }
        if (mPullToLoadText != null) {
            mPullToLoadText.setVisibility(View.GONE);
        }
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
        if (mLoadingText != null) {
            mLoadingText.setVisibility(View.GONE);
        }
        if (mFailureTextView != null) {
            mFailureTextView.setVisibility(View.GONE);
        }
    }
}
