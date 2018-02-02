package com.wkw.uiframework.base;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.wkw.ext.utils.SdkVersionUtils;
import com.wkw.uiframework.AppManager;


/**
 * Created by wukewei on 2017/8/28.
 */

public class BaseActivity extends AppCompatActivity {

    private View mBusinessView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }


    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected String getParam(@NonNull String key) {
        Intent intent = getIntent();
        if (intent != null) {
            return intent.getStringExtra(key);
        }
        return null;
    }

    /**
     * 设置一个放业务主体内容的view,用来统一处理背景等
     *
     * @param businessView businessView
     */
    protected void setBusinessView(View businessView) {
        mBusinessView = businessView;
    }


    /**
     * 设置一个背景图,为节省内存,此图应该各UI页面共用
     *
     * @param drawable 背景大图
     */
    public void setBackgroundDrawable(Drawable drawable) {
        setViewBackgroundDrawable(mBusinessView != null ? mBusinessView : getWindow().getDecorView(), drawable);
    }

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

    protected void setFullscreen(boolean fullscreen) {
        Window window = getWindow();
        WindowManager.LayoutParams winParams = window.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        if (fullscreen) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        window.setAttributes(winParams);
    }

    @Override
    protected void onDestroy() {
        setViewBackgroundDrawable(mBusinessView != null ? mBusinessView : getWindow().getDecorView(), null);
        mBusinessView = null;
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
        if (getExitAnimation() > 0) {
            overridePendingTransition(0, getExitAnimation());
        }

    }

    protected int getExitAnimation() {
        return 0;
    }
}
