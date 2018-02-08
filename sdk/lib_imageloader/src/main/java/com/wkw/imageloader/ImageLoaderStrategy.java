package com.wkw.imageloader;

import android.content.Context;

/**
 * Created by GoGo on 2018-2-5.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public interface ImageLoaderStrategy<T extends ImageConfig> {
    void loadImage(Context context, T t);
}
