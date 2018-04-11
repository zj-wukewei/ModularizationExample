package com.wkw.commonbusiness.module.archives;

import android.widget.Toast;

import com.wkw.basic.R;
import com.wkw.uiframework.module.Module;

import timber.log.Timber;

/**
 * Created by wukewei on 2017/8/27.
 */

public class DefaultArchivesModule extends Module<IArchivesUi, IArchivesService> {


    private IArchivesService service = () -> "DefaultKnowledgeModule";

    private IArchivesUi archivesUi = (context) -> {
        String msg = context.getString(R.string.basic_development);
        Timber.d(getName(), msg);
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
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
        return "DefaultArchivesModule";
    }

    @Override
    public int getVersion() {
        return 0;
    }
}
