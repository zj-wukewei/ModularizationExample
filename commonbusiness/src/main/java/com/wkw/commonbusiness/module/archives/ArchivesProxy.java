package com.wkw.commonbusiness.module.archives;

import com.wkw.commonbusiness.module.knowledge.DefaultKnowledgeModule;
import com.wkw.commonbusiness.module.knowledge.IKnowledgeService;
import com.wkw.commonbusiness.module.knowledge.IKnowledgeUi;
import com.wkw.uiframework.module.Module;
import com.wkw.uiframework.module.Proxy;

/**
 * Created by wukewei on 2017/8/27.
 */

public class ArchivesProxy extends Proxy<IKnowledgeUi, IKnowledgeService> {

    public static final ArchivesProxy g = new ArchivesProxy();

    @Override
    public String getModuleClassName() {
        return "com.wkw.archives.module.ArchivesModule";
    }

    @Override
    public Module<IKnowledgeUi, IKnowledgeService> getDefaultModule() {
        return new DefaultKnowledgeModule();
    }
}
