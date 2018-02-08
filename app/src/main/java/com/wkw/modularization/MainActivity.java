package com.wkw.modularization;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.wkw.archives.view.ArchivesActivity;
import com.wkw.commonbusiness.activity.MrActivity;
import com.wkw.imageloader.ImageLoader;
import com.wkw.imageloader.glide.GlideImageConfig;
import com.wkw.imageloader.glide.GlideImageLoaderStrategy;
import com.wkw.knowledge.KnowledgeActivity;

public class MainActivity extends MrActivity {

    private static final String TAG = "MainActivity";
    private static final String IMAGE_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517917648299&di=020b081181b4c8a8b92ddd361ec848ec&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2F201612%2F20%2F5692e458539317ac49d35fc40658c5ac.jpg";

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

        ImageLoader.getInstance().setImageLoaderStrategy(new GlideImageLoaderStrategy());

        GlideImageConfig configCircle = GlideImageConfig.builder()
                .isCircle(true)
                .url(IMAGE_URL)
                .imageView(mImgCircle)
                .build();

        ImageLoader.getInstance().displayImage(this, configCircle);


        GlideImageConfig configBlur = GlideImageConfig.builder()
                .isBlur(true)
                .url(IMAGE_URL)
                .imageView(mImgBlur)
                .build();

        ImageLoader.getInstance().displayImage(this, configBlur);

        GlideImageConfig configRound = GlideImageConfig.builder()
                .isCircle(false)
                .roundRadius(45)
                .imageView(mImgRound)
                .url(IMAGE_URL)
                .build();

        ImageLoader.getInstance().displayImage(this, configRound);

    }

    @Override
    protected String pageName() {
        return TAG;
    }
}
