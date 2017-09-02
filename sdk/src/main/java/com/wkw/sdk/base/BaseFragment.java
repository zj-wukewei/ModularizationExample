package com.wkw.sdk.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by wukewei on 2017/8/28.
 */

public class BaseFragment extends Fragment {

    protected String getStringParam(@NonNull String key) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return bundle.getString(key);
        }
        return null;
    }

    protected int getIntParam(@NonNull String key) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            return bundle.getInt(key);
        }
        return 0;
    }

}
