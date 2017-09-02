package com.wkw.commonbusiness.fragment;

import android.content.Context;

import com.wkw.commonbusiness.activity.MrActivity;
import com.wkw.sdk.base.BaseFragment;

/**
 * Created by wukewei on 2017/8/28.
 */

public class MrFragment extends BaseFragment {

    protected MrActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MrActivity) {
            mActivity = (MrActivity) context;
        }
    }
}
