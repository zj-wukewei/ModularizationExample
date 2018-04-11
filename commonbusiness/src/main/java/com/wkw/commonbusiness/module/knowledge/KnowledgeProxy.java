package com.wkw.commonbusiness.module.knowledge;

import com.wkw.uiframework.module.Module;
import com.wkw.uiframework.module.Proxy;

/**
 * Created by wukewei on 2017/8/27.
 */

public class KnowledgeProxy extends Proxy<IKnowledgeUi, IKnowledgeService> {

    public static final KnowledgeProxy g = new KnowledgeProxy();

    @Override
    public String getModuleClassName() {
        return "com.wkw.knowledge.module.KnowledgeModule";
    }

    @Override
    public Module<IKnowledgeUi, IKnowledgeService> getDefaultModule() {
        return new DefaultKnowledgeModule();
    }
}
