package com.wkw.uiframework.base.mvp;

import android.content.Context;

import androidx.annotation.StringRes;
import androidx.lifecycle.LifecycleOwner;

/**
 * Created by GoGo on 2018-4-9.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public interface MvpView extends LifecycleOwner {
    Context context();

    void showToast(@StringRes int stringId);

}
