package com.wkw.knowledge.service;

import android.support.annotation.Keep;

import com.google.auto.service.AutoService;
import com.wkw.commonbusiness.service.IKnowledgeService;
import com.wkw.uiframework.app.IAutoServiceKey;

@AutoService(IKnowledgeService.class)
@IAutoServiceKey("knowledge")
@Keep
public class KnowledgeService implements IKnowledgeService {
    @Override
    public String getName() {
        return "KnowledgeService";
    }
}
