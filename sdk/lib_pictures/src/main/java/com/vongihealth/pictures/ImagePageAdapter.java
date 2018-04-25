package com.vongihealth.pictures;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by GoGo on 2018-2-22.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class ImagePageAdapter extends FragmentStatePagerAdapter {

    private final List<String> mUrls;

    public ImagePageAdapter(@NonNull List<String> urls, FragmentManager fm) {
        super(fm);
        mUrls = urls;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(mUrls.get(position));
    }

    @Override
    public int getCount() {
        return mUrls == null ? 0 : mUrls.size();
    }
}
