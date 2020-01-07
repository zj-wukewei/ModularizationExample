package com.vongihealth.pictures;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.vongihealth.pictures.utils.RxImage;
import com.vongihealth.pictures.utils.Shares;
import com.vongihealth.pictures.widget.photoview.OnPhotoTapListener;
import com.vongihealth.pictures.widget.photoview.PhotoView;
import com.wkw.ext.utils.ToastUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * Created by GoGo on 2018-2-22.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class ImageFragment extends Fragment {
    private static final String KEY_IMAGE_RES = "com.wkw.picture.key.url";

    public static ImageFragment newInstance(String url) {
        ImageFragment fragment = new ImageFragment();
        Bundle argument = new Bundle();
        argument.putString(KEY_IMAGE_RES, url);
        fragment.setArguments(argument);
        return fragment;
    }

    private PhotoView mImageView;
    private String mUrl;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_image, container, false);
        mImageView = view.findViewById(R.id.image);
        mImageView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                getActivity().supportFinishAfterTransition();
            }
        });
        mImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                initDialog();
                return false;
            }
        });
        mUrl = getArguments().getString(KEY_IMAGE_RES);
        ViewCompat.setTransitionName(mImageView, mUrl);
        Glide.with(this)
                .load(mUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        getActivity().supportStartPostponedEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        getActivity().supportStartPostponedEnterTransition();
                        return false;
                    }
                })
                .into(mImageView);
        return view;
    }

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(new CharSequence[]{"保存图片", "发给朋友"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    saveImage();
                } else if (which == 1) {
                    share();
                }
            }
        }).show();
    }

    private void share() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .flatMap(new Function<Boolean, ObservableSource<Uri>>() {
                    @Override
                    public ObservableSource<Uri> apply(Boolean allowed) throws Exception {
                        if (allowed) {
                            return RxImage.saveImageToLocal(getActivity(), mUrl);
                        } else {
                            return Observable.error(new Exception("请开启权限"));
                        }
                    }
                })
                .subscribe(new Consumer<Uri>() {
                    @Override
                    public void accept(Uri uri) throws Exception {
                        Shares.shareImage(getActivity(), uri, "图片");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.d(throwable);
                        ToastUtils.show(getActivity(), "分享失败..");
                    }
                });
    }

    private void saveImage() {
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .flatMap(new Function<Boolean, ObservableSource<Uri>>() {
                    @Override
                    public ObservableSource<Uri> apply(Boolean allowed) throws Exception {
                        Timber.d("apply %s", String.valueOf(allowed));
                        if (allowed) {
                            return RxImage.saveImageToLocal(getActivity(), mUrl);
                        } else {
                            return Observable.error(new Exception("请开启权限"));
                        }
                    }
                })
                .subscribe(new Consumer<Uri>() {
                    @Override
                    public void accept(Uri uri) throws Exception {
                        ToastUtils.show(getActivity(), "已保存");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.d(throwable);
                        ToastUtils.show(getActivity(), "保存图片失败..");
                    }
                });
    }


}
