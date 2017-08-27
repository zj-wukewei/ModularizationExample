package com.wkw.baisc.module.user;

import com.wkw.baisc.module.Module;
import com.wkw.baisc.module.Proxy;

/**
 * Created by wukewei on 2017/8/27.
 */

public class UserProxy extends Proxy<IUserUi, IUserService> {
    @Override
    public String getModuleClassName() {
        return null;
    }

    @Override
    public Module<IUserUi, IUserService> getDefaultModule() {
        return new DefaultUserModule();
    }
}
