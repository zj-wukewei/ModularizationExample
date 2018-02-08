package com.wkw.imageloader.glide;

import android.content.res.Resources;
import android.widget.ImageView;

import com.wkw.imageloader.ImageConfig;

/**
 * Created by GoGo on 2018-2-5.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class GlideImageConfig extends ImageConfig {
    private boolean isCircle;
    private int roundRadius;
    private int cacheStrategy;
    private boolean isBlur;

    public GlideImageConfig(Builder builder) {
        this.url = builder.url;
        this.imageView = builder.imageView;
        this.placeholder = builder.placeholder;
        this.errorPic = builder.errorPic;
        this.isCircle = builder.isCircle;
        this.roundRadius = builder.roundRadius;
        this.cacheStrategy = builder.cacheStrategy;
        this.isBlur = builder.isBlur;
    }

    public boolean isBlur() {
        return isBlur;
    }

    public int getCacheStrategy() {
        return cacheStrategy;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public int getRoundRadius() {
        return roundRadius;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String url;
        private ImageView imageView;
        private int placeholder;
        private int errorPic;
        private boolean isCircle;
        private boolean isBlur;
        private int roundRadius;
        private int cacheStrategy;//0对应DiskCacheStrategy.all,1对应DiskCacheStrategy.NONE,2对应DiskCacheStrategy.SOURCE,3对应DiskCacheStrategy.RESULT

        private Builder() {
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeholder(int placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public Builder errorPic(int errorPic) {
            this.errorPic = errorPic;
            return this;
        }

        public Builder cacheStrategy(int cacheStrategy) {
            this.cacheStrategy = cacheStrategy;
            return this;
        }

        public Builder imageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }


        public Builder isCircle(boolean isCircle) {
            this.isCircle = isCircle;
            return this;
        }

        public Builder isBlur(boolean isBlur) {
            this.isBlur = isBlur;
            return this;
        }

        public Builder roundRadius(int roundRadiusDp) {
            final int radius = (int) (Resources.getSystem().getDisplayMetrics().density * roundRadiusDp);
            this.roundRadius = radius;
            return this;
        }


        public GlideImageConfig build() {
            if (url == null) throw new IllegalStateException("url is required");
            if (imageView == null) throw new IllegalStateException("imageview is required");
            return new GlideImageConfig(this);
        }
    }
}
