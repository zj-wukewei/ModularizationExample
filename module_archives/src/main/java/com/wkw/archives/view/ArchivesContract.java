package com.wkw.archives.view;

import com.wkw.commonbusiness.entity.TokenEntity;
import com.wkw.uiframework.base.mvp.MvpPresenter;
import com.wkw.uiframework.base.mvp.rxandroid.LoadingView;

/**
 * Created by wukewei on 2017/9/12.
 */

public class ArchivesContract {

    public interface View extends LoadingView {

        void showData(TokenEntity entity);

        void showDataString(String entity);

        void showName(String name);

        void showPassword(String pa);
    }

    public interface Presenter extends MvpPresenter<View> {
        void archivesList(int pn);

        void fetchName();

        void fetchPassword();
    }
}
