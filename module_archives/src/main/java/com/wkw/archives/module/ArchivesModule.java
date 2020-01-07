package com.wkw.archives.module;

import android.content.Intent;

import androidx.annotation.Keep;

import com.wkw.archives.view.ArchivesActivity;
import com.wkw.commonbusiness.module.archives.IArchivesService;
import com.wkw.commonbusiness.module.archives.IArchivesUi;
import com.wkw.uiframework.module.Module;

/**
 * Created by wukewei on 2017/9/12.
 */
@Keep
public class ArchivesModule extends Module<IArchivesUi, IArchivesService> {

    private IArchivesService service = () -> "ArchivesModule";

    private IArchivesUi archivesUi = (context) -> {
        if (context != null) {
            Intent intent = new Intent(context, ArchivesActivity.class);
            context.startActivity(intent);
        }
    };

    @Override
    public IArchivesUi getUiInterface() {
        return archivesUi;
    }

    @Override
    public IArchivesService getServiceInterface() {
        return service;
    }

    @Override
    public String getName() {
        return "ArchivesModule";
    }

    @Override
    public int getVersion() {
        return 0;
    }
}
