package com.vongihealth.pictures.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by GoGo on 2018-2-23.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class RxImage {

    public static Observable<Uri> saveImageToLocal(final Context context, final String url) {
        return Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(ObservableEmitter<File> e) throws Exception {
                File file = null;
                try {
                    file = Glide.with(context).load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
                } catch (Exception e1) {
                    e.onError(e1);
                }

                if (file == null) {
                    e.onError(new Exception("无法下载到图片"));
                }

                e.onNext(file);
                e.onComplete();
            }
        }).flatMap(new Function<File, ObservableSource<Uri>>() {
            @Override
            public ObservableSource<Uri> apply(File file) throws Exception {
                File mFile;
                FileInputStream fis = null;
                FileOutputStream fos = null;
                try {
                    String path = Environment.getExternalStorageDirectory() + File.separator + "vongihealth";
                    File dir = new File(path);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    String fileName = System.currentTimeMillis() + ".png";
                    mFile = new File(dir, fileName);
                    fis = new FileInputStream(file.getAbsolutePath());

                    int byteRead = 0;
                    byte[] buf = new byte[1444];

                    fos = new FileOutputStream(mFile.getAbsolutePath());
                    while ((byteRead = fis.read(buf)) != -1) {
                        fos.write(buf, 0, byteRead);
                    }
                } catch (IOException e) {
                    throw e;
                } finally {
                    fos.close();
                    fis.close();
                }
                //更新本地图库
                Uri uri = Uri.fromFile(mFile);
                Intent mIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
                context.sendBroadcast(mIntent);
                return Observable.just(uri);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

//    public static Flowable<Uri> saveImageToLocal(final Context context, final String url) {
//        return Flowable.fromCallable(new Callable<File>() {
//            @Override
//            public File call() throws Exception {
//                return Glide.with(context).load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
//            }
//        }).flatMap(new Function<File, Publisher<Uri>>() {
//            @Override
//            public Publisher<Uri> apply(File file) throws Exception {
//                File mFile;
//                FileInputStream fis = null;
//                FileOutputStream fos = null;
//                try {
//                    String path = Environment.getExternalStorageDirectory() + File.separator + "vongihealth";
//                    File dir = new File(path);
//                    if (!dir.exists()) {
//                        dir.mkdirs();
//                    }
//                    String fileName = System.currentTimeMillis() + ".png";
//                    mFile = new File(dir, fileName);
//                    fis = new FileInputStream(file.getAbsolutePath());
//
//                    int byteRead = 0;
//                    byte[] buf = new byte[1444];
//
//                    fos = new FileOutputStream(mFile.getAbsolutePath());
//                    while ((byteRead = fis.read(buf)) != -1) {
//                        fos.write(buf, 0, byteRead);
//                    }
//                } catch (IOException e) {
//                    throw e;
//                } finally {
//                    fos.close();
//                    fis.close();
//                }
//                //更新本地图库
//                Uri uri = Uri.fromFile(mFile);
//                Intent mIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
//                context.sendBroadcast(mIntent);
//                return Flowable.just(uri);
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }
}
