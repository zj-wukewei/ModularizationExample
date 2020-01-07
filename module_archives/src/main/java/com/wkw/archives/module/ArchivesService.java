package com.wkw.archives.module;


import androidx.annotation.Keep;

import com.google.auto.service.AutoService;
import com.wkw.commonbusiness.service.IArchivesService;
import com.wkw.uiframework.app.IAutoServiceKey;

@AutoService(IArchivesService.class)
@IAutoServiceKey("archives")
@Keep
public class ArchivesService implements IArchivesService {
    @Override
    public String getName() {
        return "KnowledgeService";
    }
}
