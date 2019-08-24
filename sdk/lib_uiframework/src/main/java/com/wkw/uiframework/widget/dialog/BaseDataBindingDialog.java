package com.wkw.uiframework.widget.dialog;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.wkw.ext.utils.ViewUtils;
import com.wkw.uiframework.R;

/**
 * @author GoGo on 2019-08-24.
 */
public abstract class BaseDataBindingDialog<DB extends ViewDataBinding> extends DialogFragment {

    private static final String KEY_LAYOUT_RES = "base_dialog_layout_res";
    private static final String KEY_HEIGHT = "base_dialog_height";
    private static final String KEY_WIDTH_PADDING = "KEY_WIDTH_PADDING";
    private static final String KEY_DIM = "base_dialog_dim";
    private static final String KEY_BOTTOM = "base_dialog_bottom";
    private static final String KEY_CANCEL_OUTSIDE = "base_dialog_cancel_outside";


    private static final String TAG = "BaseDataBindingDialog";

    private static final float DEFAULT_DIM = 0.5f;

    protected DB mBinding;

    private boolean mIsCancelOutside = getCancelOutside();

    private String mTag = getFragmentTag();

    private float mDimAmount = getDimAmount();

    private int mHeight = getHeight();

    private int mWidthPadding = getWidthPadding();

    private boolean mIsBottom = isBottom();

    @LayoutRes
    private int mLayoutRes = getLayoutRes();

    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomDialog);
        if (savedInstanceState != null) {
            mLayoutRes = savedInstanceState.getInt(KEY_LAYOUT_RES);
            mHeight = savedInstanceState.getInt(KEY_HEIGHT);
            mWidthPadding = savedInstanceState.getInt(KEY_WIDTH_PADDING);
            mDimAmount = savedInstanceState.getFloat(KEY_DIM);
            mIsCancelOutside = savedInstanceState.getBoolean(KEY_CANCEL_OUTSIDE);
            mIsBottom = savedInstanceState.getBoolean(KEY_BOTTOM);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_LAYOUT_RES, mLayoutRes);
        outState.putInt(KEY_HEIGHT, mHeight);
        outState.putInt(KEY_WIDTH_PADDING, mWidthPadding);
        outState.putFloat(KEY_DIM, mDimAmount);
        outState.putBoolean(KEY_CANCEL_OUTSIDE, mIsCancelOutside);
        outState.putBoolean(KEY_CANCEL_OUTSIDE, mIsBottom);
        super.onSaveInstanceState(outState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(mIsCancelOutside);
        mBinding = DataBindingUtil.inflate(inflater, mLayoutRes, container, false);
        bindView();
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = getDimAmount();
        if (mWidthPadding > 0) {
            params.width = ViewUtils.getScreenWidth() - ViewUtils.dpToPx(mWidthPadding);
        } else {
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
        }

        if (mHeight > 0) {
            params.height = mHeight;
        } else {
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }

        if (mIsBottom) {
            params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        } else {
            params.gravity = Gravity.CENTER;
        }

        window.setAttributes(params);
    }


    public boolean isBottom () {
        return false;
    }


    @LayoutRes
    public abstract int getLayoutRes();

    public abstract void bindView();


    public int getHeight() {
        return -1;
    }

    //默认padding30dp
    public int getWidthPadding() {
        return 40;
    }

    public float getDimAmount() {
        return DEFAULT_DIM;
    }

    public boolean getCancelOutside() {
        return true;
    }

    public String getFragmentTag() {
        return TAG;
    }


}
