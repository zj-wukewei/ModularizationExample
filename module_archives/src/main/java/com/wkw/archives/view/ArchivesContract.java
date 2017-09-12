package com.wkw.archives.view;

import com.wkw.archives.domain.entity.ArchivesEntity;
import com.wkw.commonbusiness.mvp.MvpPresenter;
import com.wkw.commonbusiness.mvp.MvpView;

/**
 * Created by wukewei on 2017/9/12.
 */

public class ArchivesContract {

    public interface View extends MvpView {
        void showLoading();
        void showData(ArchivesEntity entity);
        void hideLoading();
    }

    public interface Presenter extends MvpPresenter<View> {
        void archivesList(int pn);
    }
}
