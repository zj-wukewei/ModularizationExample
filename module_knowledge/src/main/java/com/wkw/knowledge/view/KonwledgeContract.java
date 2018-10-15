package com.wkw.knowledge.view;

import com.wkw.commonbusiness.entity.AbstractQry;
import com.wkw.knowledge.entity.User;
import com.wkw.uiframework.base.mvp.page.PageEntity;
import com.wkw.uiframework.base.mvp.page.PagePresenter;
import com.wkw.uiframework.base.mvp.page.PageView;

/**
 * Created by wukewei on 2017/9/12.
 */

public class KonwledgeContract {

    public interface View extends PageView<String> {
        void showDataUserList(PageEntity<User> users);
    }

    public interface Presenter extends PagePresenter<Integer, String, View> {
        void usersList(AbstractQry qry);
    }
}
