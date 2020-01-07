package com.wkw.imageloader.glide;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import java.security.MessageDigest;

/**
 * Created by GoGo on 2018-2-5.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class GlideCircleTransform extends BitmapTransformation {

    private static final int VERSION = 1;
    private static final String ID =
            "jp.wasabeef.glide.transformations.CropCircleTransformation." + VERSION;
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    @Override
    public String toString() {
        return "GlideCircleTransform()";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GlideCircleTransform;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.circleCrop(pool, toTransform, outWidth, outHeight);
    }
}
