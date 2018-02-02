package com.wkw.uiframework.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wkw.ext.Ext;
import com.wkw.ext.utils.KeyBoardUtils;
import com.wkw.ext.utils.SdkVersionUtils;

/**
 * Created by wukewei on 2017/8/28.
 */

public class BaseFragment extends Fragment {

    private View mBusinessView;

    private static Drawable sGlobalBackgroundDrawable;

    /**
     * @return 全局背景图
     */
    public static Drawable getGlobalBackgroundDrawable() {
        return sGlobalBackgroundDrawable;
    }

    /**
     * 设置全局背景图,仅仅记录,新fragment打开的时候自动应用
     *
     * @param globalBackgroundDrawable 全局背景图
     */
    public static void setGlobalBackgroundDrawable(Drawable globalBackgroundDrawable) {
        sGlobalBackgroundDrawable = globalBackgroundDrawable;
    }


    /**
     * 设置一个背景图,为节省内存,此图应该各UI页面共用
     *
     * @param drawable 背景大图
     */
    public void setBackgroundDrawable(Drawable drawable) {
        if (mBusinessView != null) {
            setViewBackgroundDrawable(mBusinessView, drawable);
        }
    }

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

    /**
     * 给某个view设置一个background
     *
     * @param view     view
     * @param drawable drawable
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setViewBackgroundDrawable(View view, Drawable drawable) {
        if (view != null) {
            if (SdkVersionUtils.hasJellyBean()) {
                view.setBackground(drawable);
            } else {
                view.setBackgroundDrawable(drawable);
            }
        }
    }

    public void hideInputMethod() {
        Activity activity = getActivity();
        if (activity != null) {
            KeyBoardUtils.hideInputMethod(activity, activity.getCurrentFocus());
        }
    }

    public void showInputMethod(EditText editText) {
        Activity activity = getActivity();
        KeyBoardUtils.showInputMethod(activity, editText);
    }

    /**
     * Shows a {@link Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public String getMrString(int resId) {
        return Ext.getContext().getString(resId);
    }

    @Override
    public void onDetach() {
        setViewBackgroundDrawable(mBusinessView != null ? mBusinessView : getView(), null);
        super.onDetach();
        mBusinessView = null;
    }

}
