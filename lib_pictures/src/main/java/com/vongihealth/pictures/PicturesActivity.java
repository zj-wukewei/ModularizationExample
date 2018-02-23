package com.vongihealth.pictures;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vongihealth.pictures.widget.MultiTouchViewPager;
import com.wkw.ext.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by GoGo on 2018-2-22.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class PicturesActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    public static final String IMAGE_INDEX = "index";

    private static final String IMAGE_URL = "image_url";
    private static final String START = "start";

    private MultiTouchViewPager mViewPager;
    private TextView mTvCurrent;

    private ArrayList<String> mUrls;
    private int mCurrent;

    public static void startActivity(Context context, @NonNull String url) {
        startActivity(context, Collections.singletonList(url), 0);
    }

    public static void startActivity(Activity context, ImageView imageView, @NonNull String url) {
        Intent intent = new Intent(context, PicturesActivity.class);
        intent.putExtra(IMAGE_URL, new ArrayList<>(Arrays.asList(url)));
        intent.putExtra(START, 0);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(context, imageView, url);
        try {
            context.startActivity(intent, options.toBundle());
        } catch (Exception e) {
            context.startActivity(intent);
        }
    }


    /***
     *  多张图片页面元素共享，请在进入的activity，设置
     *
     *  setExitSharedElementCallback(
     *       new SharedElementCallback() {
     *       @Override
     *       public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
     *           // Locate the ViewHolder for the clicked position.
     *           // Map the first shared element name to the child ImageView.
     *           if (index != -1) {
     *               sharedElements.put(names.get(0), imageViews.get(index));
     *           }
     *       }
     *   });
     *
     *    @Override
     *    public void onActivityReenter(int resultCode, Intent data) {
     *        super.onActivityReenter(resultCode, data);
     *        index = data.getIntExtra(PicturesActivity.IMAGE_INDEX, -1);
     *    }
     *    才能生效
     * @param context  activity
     * @param imageView 启动的imageView
     * @param urls 图片链接
     * @param start 开始位置
     * @return
     */
    public static void startActivity(Activity context, ImageView imageView, @NonNull List<String> urls, int start) {
        Intent intent = new Intent(context, PicturesActivity.class);
        intent.putExtra(IMAGE_URL, new ArrayList<>(urls));
        intent.putExtra(START, start);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(context, imageView, urls.get(start));
        try {
            context.startActivity(intent, options.toBundle());
        } catch (Exception e) {
            context.startActivity(intent);
        }
    }

    public static void startActivity(Context context, @NonNull List<String> urls, int start) {
        if (start < 0 || start > urls.size()) {
            throw new IllegalArgumentException(start + "must be less than +" + urls.size() + "and more than 0");
        }
        Intent intent = new Intent(context, PicturesActivity.class);
        intent.putExtra(IMAGE_URL, new ArrayList<>(urls));
        intent.putExtra(START, start);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.black));
        mViewPager = findViewById(R.id.view_page);
        mTvCurrent = findViewById(R.id.tv_current);


        mUrls = getIntent().getStringArrayListExtra(IMAGE_URL);
        if (mUrls == null) {
            mUrls = new ArrayList<>();
        }
        mCurrent = getIntent().getIntExtra(START, 0);

        if (mUrls.isEmpty() || mUrls.size() == 1) {
            mTvCurrent.setVisibility(View.GONE);
        } else {
            mTvCurrent.setText(getString(R.string.current_size, mCurrent +1 , mUrls.size()));
        }


        mViewPager.setAdapter(new ImagePageAdapter(mUrls, getSupportFragmentManager()));
        mViewPager.setCurrentItem(mCurrent);
        mViewPager.addOnPageChangeListener(this);

        prepareSharedElementTransition();
        supportPostponeEnterTransition();
    }

    private void prepareSharedElementTransition() {
        setEnterSharedElementCallback(
                new SharedElementCallback() {
                    @Override
                    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                        Fragment currentFragment = (Fragment) mViewPager.getAdapter()
                                .instantiateItem(mViewPager, mViewPager.getCurrentItem());
                        View view = currentFragment.getView();
                        if (view == null) {
                            return;
                        }
                        sharedElements.put(names.get(0), view.findViewById(R.id.image));
                    }
                });
    }


    @Override
    public void supportFinishAfterTransition() {
        Intent data = new Intent();
        data.putExtra(IMAGE_INDEX, mViewPager.getCurrentItem());
        setResult(RESULT_OK, data);
        super.supportFinishAfterTransition();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            supportFinishAfterTransition();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrent = position;
        mTvCurrent.setText(getString(R.string.current_size, mCurrent +1 , mUrls.size()));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
