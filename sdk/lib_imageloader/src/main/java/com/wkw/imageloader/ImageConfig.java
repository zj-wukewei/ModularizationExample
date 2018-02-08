package com.wkw.imageloader;

import android.widget.ImageView;

/**
 * Created by GoGo on 2018-2-5.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class ImageConfig {
    protected ImageView imageView;
    protected String url;
    protected int placeholder;
    protected int errorPic;

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(int placeholder) {
        this.placeholder = placeholder;
    }

    public int getErrorPic() {
        return errorPic;
    }

    public void setErrorPic(int errorPic) {
        this.errorPic = errorPic;
    }

}
