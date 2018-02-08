package com.wkw.imageloader;

import android.content.Context;

/**
 * Created by GoGo on 2018-2-5.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class ImageLoader {
    private ImageLoaderStrategy mImageLoaderStrategy;

    private ImageLoader() {
    }


    public void setImageLoaderStrategy(ImageLoaderStrategy imageLoaderStrategy) {
        this.mImageLoaderStrategy = imageLoaderStrategy;
    }


    public <T extends ImageConfig> void displayImage(Context context, T config) {
        if (this.mImageLoaderStrategy == null) {
            throw new RuntimeException("请调用ImageLoaderStrategy设置ImageLoaderStrategy!");
        }
        this.mImageLoaderStrategy.loadImage(context, config);
    }


    public static ImageLoader getInstance() {
        return ImageLoaderInstance.mImageLoader;
    }


    public static class ImageLoaderInstance {
        private final static ImageLoader mImageLoader = new ImageLoader();
    }

}
