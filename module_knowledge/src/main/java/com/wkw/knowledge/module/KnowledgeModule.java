package com.wkw.knowledge.module;

import android.content.Intent;

import androidx.annotation.Keep;

import com.wkw.commonbusiness.module.knowledge.IKnowledgeService;
import com.wkw.commonbusiness.module.knowledge.IKnowledgeUi;
import com.wkw.knowledge.KnowledgeActivity;
import com.wkw.uiframework.module.Module;

/**
 * Created by wukewei on 2017/9/12.
 */
@Keep
public class KnowledgeModule extends Module<IKnowledgeUi, IKnowledgeService> {

    private IKnowledgeService service = () -> "KnowledgeModule";

    private IKnowledgeUi knowledgeUi = (context) -> {
        if (context != null) {
            Intent intent = new Intent(context, KnowledgeActivity.class);
            context.startActivity(intent);
        }
    };

    @Override
    public IKnowledgeUi getUiInterface() {
        return knowledgeUi;
    }

    @Override
    public IKnowledgeService getServiceInterface() {
        return service;
    }

    @Override
    public String getName() {
        return "KnowledgeModule";
    }

    @Override
    public int getVersion() {
        return 0;
    }
}
