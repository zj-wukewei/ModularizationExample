package com.wkw.commonbusiness.module.knowledge;

import android.widget.Toast;

import com.wkw.basic.R;
import com.wkw.commonbusiness.module.Module;

import timber.log.Timber;

/**
 * Created by wukewei on 2017/8/27.
 */

public class DefaultKnowledgeModule extends Module<IKnowledgeUi, IKnowledgeService> {


    private IKnowledgeService service = () -> "DefaultKnowledgeModule";

    private IKnowledgeUi knowledgeUi = (context) -> {
        String msg = context.getString(R.string.basic_development);
        Timber.d(getName(), msg);
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
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
        return "DefaultKnowledgeModule";
    }

    @Override
    public int getVersion() {
        return 0;
    }
}
