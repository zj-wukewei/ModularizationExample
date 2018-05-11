package com.wkw.knowledge.view;

import com.wkw.uiframework.base.mvp.page.PagePresenter;
import com.wkw.uiframework.base.mvp.page.PageView;

/**
 * Created by wukewei on 2017/9/12.
 */

public class KonwledgeContract {

    public interface View extends PageView<String> {


    }

    public interface Presenter extends PagePresenter<Integer, String, View> {

    }
}
