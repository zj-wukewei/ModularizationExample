package com.wkw.imageloader.glide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wkw.imageloader.ImageLoaderStrategy;
import com.wkw.imageloader.utils.ImageUtil;

/**
 * Created by GoGo on 2018-2-5.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class GlideImageLoaderStrategy implements ImageLoaderStrategy<GlideImageConfig> {
    @SuppressLint("CheckResult")
    @Override
    public void loadImage(Context context, GlideImageConfig config) {

        if (TextUtils.isEmpty(config.getUrl())) {
            config.getImageView()
                    .setImageDrawable(ContextCompat.getDrawable(context, config.getErrorPic()));
            return;
        }

        RequestManager manager;
        if (context instanceof Activity) {
            manager = Glide.with((Activity) context);
        } else {
            manager = Glide.with(context);
        }

        RequestOptions requestOptions = new RequestOptions();

        if (config.getErrorPic() != 0) {
            requestOptions.error(config.getErrorPic());
        }
        if (config.getPlaceholder() != 0) {
            requestOptions.placeholder(config.getPlaceholder());
        }
        if (config.isCircle()) {
            requestOptions.transform(new GlideCircleTransform());
        }

        switch (config.getCacheStrategy()) {
            case 0:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case 1:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case 2:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.DATA);
                break;
            case 3:
                requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                break;
            default:
                break;
        }

        if (!config.isCircle() && config.getRoundRadius() != 0) {
            requestOptions.transform(new GlideRoundTransform(config.getRoundRadius()));
        }

        if (config.isBlur()) {
            requestOptions.transform(new BlurTransformation(config.getImageView().getContext().getApplicationContext()));
        }

        RequestBuilder<Drawable> request = manager.load(config.getUrl());


        request.apply(requestOptions).into(config.getImageView());

    }

    @Override
    public String getCacheSize(Context context) {
        try {
            return ImageUtil.getFormatSize(ImageUtil.getFolderSize(Glide.getPhotoCacheDir(context.getApplicationContext())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public void clearImageDiskCache(Context context) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("You should not use the Live Transformer at a background thread.");
        }
        Glide.get(context.getApplicationContext()).clearMemory();
    }

    @Override
    public void clearImageMemoryCache(final Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Glide.get(context.getApplicationContext()).clearDiskCache();
                }
            }).start();
        } else {
            Glide.get(context.getApplicationContext()).clearDiskCache();
        }
    }

}
