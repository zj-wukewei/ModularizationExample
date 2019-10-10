package com.wkw.knowledge.view;

import com.wkw.uiframework.base.mvp.page.PageFragment;
import com.wkw.uiframework.base.mvp.page.PagePresenter;
import com.wkw.uiframework.base.mvp.page.PageView;

import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.OnFlingOverListener;

/**
 * Created by GoGo on 2019/10/10.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */
public abstract class FragmentPagerFragment<Request, Response, V extends PageView<Response>, P extends PagePresenter<Request, Response, V>> extends PageFragment<Request, Response, V, P> implements CanScrollVerticallyDelegate, OnFlingOverListener {

}
