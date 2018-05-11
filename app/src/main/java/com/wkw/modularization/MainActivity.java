package com.wkw.modularization;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;

import com.vongihealth.pictures.PicturesActivity;
import com.wkw.archives.view.ArchivesActivity;
import com.wkw.commonbusiness.activity.MrActivity;
import com.wkw.imageloader.ImageLoader;
import com.wkw.imageloader.glide.GlideImageConfig;
import com.wkw.knowledge.KnowledgeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends MrActivity {

    private static final String TAG = "MainActivity";
    private static final String IMAGE_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517917648299&di=020b081181b4c8a8b92ddd361ec848ec&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2F201612%2F20%2F5692e458539317ac49d35fc40658c5ac.jpg";
    private static final String IMAGE_URL1 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2094526173,856654999&fm=27&gp=0.jpg";
    private static final String IMAGE_URL2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1519292977282&di=9ace918803450678d1682713a2eb489d&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F018d4e554967920000019ae9df1533.jpg%40900w_1l_2o_100sh.jpg";
    private ImageView mImgCircle;
    private ImageView mImgRound;
    private ImageView mImgBlur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgCircle = findViewById(R.id.img_circle);
        mImgRound = findViewById(R.id.img_round);
        mImgBlur = findViewById(R.id.img_blur);
        findViewById(R.id.knowledge).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, KnowledgeActivity.class));
        });

        findViewById(R.id.archives).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ArchivesActivity.class));
        });

        List<String> urls = new ArrayList<>();
        urls.add(IMAGE_URL);
        urls.add(IMAGE_URL1);
        urls.add(IMAGE_URL2);
        List<ImageView> imageViews = new ArrayList<>();
        imageViews.add(mImgCircle);
        imageViews.add(mImgRound);
        imageViews.add(mImgBlur);


        GlideImageConfig configCircle = GlideImageConfig.builder()
                .isCircle(true)
                .url(IMAGE_URL)
                .imageView(mImgCircle)
                .build();

        ViewCompat.setTransitionName(mImgCircle, IMAGE_URL);
        mImgCircle.setOnClickListener(v -> {
            index = 0;
            PicturesActivity.startActivity(MainActivity.this, mImgCircle, urls, 0);
        });

        ImageLoader.getInstance().displayImage(this, configCircle);


        ViewCompat.setTransitionName(mImgRound, IMAGE_URL1);
        GlideImageConfig configRound = GlideImageConfig.builder()
                .isCircle(false)
                .roundRadius(45)
                .imageView(mImgRound)
                .url(IMAGE_URL1)
                .build();

        mImgRound.setOnClickListener(v -> {
            index = 1;
            PicturesActivity.startActivity(MainActivity.this, urls, 1);
        });
        ImageLoader.getInstance().displayImage(this, configRound);


        ViewCompat.setTransitionName(mImgBlur, IMAGE_URL2);
        GlideImageConfig configBlur = GlideImageConfig.builder()
                .isBlur(true)
                .url(IMAGE_URL2)
                .imageView(mImgBlur)
                .build();
        mImgBlur.setOnClickListener(v -> {
            index = 2;
            PicturesActivity.startActivity(MainActivity.this, mImgBlur, urls, 2);
        });
        ImageLoader.getInstance().displayImage(this, configBlur);

        setExitSharedElementCallback(
                new SharedElementCallback() {
                    @Override
                    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                        // Locate the ViewHolder for the clicked position.
                        // Map the first shared element name to the child ImageView.
                        if (index != -1) {
                            sharedElements.put(names.get(0), imageViews.get(index));
                        }
                    }
                });
    }

    int index = -1;


    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        index = data.getIntExtra(PicturesActivity.IMAGE_INDEX, -1);
    }

    @Override
    protected String pageName() {
        return TAG;
    }
}
