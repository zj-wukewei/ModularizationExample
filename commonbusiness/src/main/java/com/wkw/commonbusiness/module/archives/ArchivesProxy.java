package com.wkw.commonbusiness.module.archives;

import com.wkw.commonbusiness.module.Module;
import com.wkw.commonbusiness.module.Proxy;
import com.wkw.commonbusiness.module.knowledge.DefaultKnowledgeModule;
import com.wkw.commonbusiness.module.knowledge.IKnowledgeService;
import com.wkw.commonbusiness.module.knowledge.IKnowledgeUi;

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
