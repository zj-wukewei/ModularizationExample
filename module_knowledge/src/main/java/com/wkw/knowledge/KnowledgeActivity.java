package com.wkw.knowledge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.wkw.commonbusiness.service.IArchivesService;
import com.wkw.knowledge.entity.User;
import com.wkw.knowledge.view.KnowledgePresenter;
import com.wkw.knowledge.view.KonwledgeContract;
import com.wkw.knowledge.view.fragment.KnowledgeFragment;
import com.wkw.uiframework.app.MrServiceLoader;
import com.wkw.uiframework.base.mvp.MvpActivity;
import com.wkw.uiframework.base.mvp.page.PageEntity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import timber.log.Timber;

/**
 * Created by wukewei on 2017/9/9.
 */

public class KnowledgeActivity extends MvpActivity<KonwledgeContract.View, KonwledgeContract.Presenter> implements KonwledgeContract.View, HasSupportFragmentInjector {

    private static final String TAG = "KnowledgeActivity";

    @Inject
    KnowledgePresenter mKnowledgePresenter;

    @Override
    protected KonwledgeContract.Presenter getPresenter() {
        return mKnowledgePresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_activity_knowledge);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment, KnowledgeFragment.newInstance());
        fragmentTransaction.commitAllowingStateLoss();
        IArchivesService service = MrServiceLoader.getInstance().getService(IArchivesService.class, "archives");
        IArchivesService service1 = MrServiceLoader.getInstance().getService(IArchivesService.class, "archives");
        if (service != null) {
            Timber.d("IArchivesService1111 %s", service.getName());
            Timber.d("IArchivesService1111 %s", service);
        }
        if (service1 != null) {
            Timber.d("IArchivesService2222 %s", service1.getName());
            Timber.d("IArchivesService2222 %s", service1);
        }
    }


    @Override
    public void showLoading() {
        Timber.d("showLoading");
    }

    @Override
    public void hideLoading() {
        Timber.d("hideLoading");
    }

    @Override
    public void showError(Exception e) {
        Timber.d("Exception");
    }

    @Override
    public void showData(PageEntity<String> data) {
        Timber.d(data.toString());
        Timber.d(data.isHasMore() + "");
        Timber.d(data.getList().size() + "");
    }

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }

    @Override
    public void showDataUserList(PageEntity<User> users) {
        Timber.d(users.toString());
    }
}
