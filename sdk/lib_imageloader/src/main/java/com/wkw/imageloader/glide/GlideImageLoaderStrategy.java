package com.wkw.imageloader.glide;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.wkw.imageloader.ImageLoaderStrategy;

/**
 * Created by GoGo on 2018-2-5.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class GlideImageLoaderStrategy implements ImageLoaderStrategy<GlideImageConfig> {
    @Override
    public void loadImage(Context context, GlideImageConfig config) {

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
}
