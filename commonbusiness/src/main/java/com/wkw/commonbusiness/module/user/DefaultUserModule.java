package com.wkw.commonbusiness.module.user;

import android.widget.Toast;

import com.wkw.basic.R;
import com.wkw.commonbusiness.module.Module;
import com.wkw.sdk.utils.Logger;

/**
 * Created by wukewei on 2017/8/27.
 */

public class DefaultUserModule extends Module<IUserUi, IUserService> {


    private IUserService service = new IUserService() {

    };

    private IUserUi userUi = (context) -> {
        String msg = context.getString(R.string.basic_development);
        Logger.d(getName(), msg);
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    };

    @Override
    public IUserUi getUiInterface() {
        return userUi;
    }

    @Override
    public IUserService getServiceInterface() {
        return service;
    }

    @Override
    public String getName() {
        return "DefaultUserModule";
    }

    @Override
    public int getVersion() {
        return 0;
    }
}
