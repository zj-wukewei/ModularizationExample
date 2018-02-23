package com.vongihealth.pictures.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by GoGo on 2018-2-23.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class Shares {
    public static void shareImage(Context context, Uri uri, String title) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        context.startActivity(Intent.createChooser(shareIntent, title));
    }
}
