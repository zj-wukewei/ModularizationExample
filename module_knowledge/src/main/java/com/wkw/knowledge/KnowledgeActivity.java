package com.wkw.knowledge;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wkw.commonbusiness.activity.MrActivity;

/**
 * Created by wukewei on 2017/9/9.
 */

public class KnowledgeActivity extends MrActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_activity_knowledge);
    }

    @Override
    protected String pageName() {
        return null;
    }
}
