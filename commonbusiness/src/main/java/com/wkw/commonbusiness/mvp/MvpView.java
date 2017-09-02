package com.wkw.commonbusiness.mvp;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by wukewei on 2017/8/28.
 */

public interface MvpView {

    Context context();

    void showError(Throwable e);

    void showToast(@StringRes int stringId);
}
