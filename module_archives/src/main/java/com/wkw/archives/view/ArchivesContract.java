package com.wkw.archives.view;

import com.wkw.commonbusiness.entity.TokenEntity;
import com.wkw.uiframework.base.mvp.MvpPresenter;
import com.wkw.uiframework.base.mvp.MvpView;

/**
 * Created by wukewei on 2017/9/12.
 */

public class ArchivesContract {

    public interface View extends MvpView {
        void showLoading();
        void showData(TokenEntity entity);
        void hideLoading();
    }

    public interface Presenter extends MvpPresenter<View> {
        void archivesList(int pn);
    }
}
