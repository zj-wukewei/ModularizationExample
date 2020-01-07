package com.vongihealth.pictures;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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
