package com.wkw.modularization;

import android.content.Intent;
import android.os.Bundle;

import com.wkw.archives.view.ArchivesActivity;
import com.wkw.commonbusiness.activity.MrActivity;
import com.wkw.knowledge.KnowledgeActivity;

public class MainActivity extends MrActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.knowledge).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, KnowledgeActivity.class));
        });

        findViewById(R.id.archives).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ArchivesActivity.class));
        });

    }

    @Override
    protected String pageName() {
        return TAG;
    }
}
