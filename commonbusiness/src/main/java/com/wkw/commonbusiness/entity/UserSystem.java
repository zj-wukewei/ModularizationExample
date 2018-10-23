package com.wkw.commonbusiness.entity;

import android.content.Context;

import com.wkw.commonbusiness.UserContentObserver;
import com.wkw.commonbusiness.utils.LoginModuleUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by wukewei on 2017/8/25.
 */

@Singleton
public class UserSystem {
    private static final String KEY_TOKEN = "token";
    private static final String KEY_USER_ID = "uid";

    private BehaviorSubject<TokenEntity> mTokenEntityBehaviorSubject = BehaviorSubject.create();


    @Inject
    public UserSystem(Context context) {
        LoginModuleUtils.registerUserContentObserver(context, new UserContentObserver(context,
                entity -> mTokenEntityBehaviorSubject.onNext(entity)));
    }


    public Observable<TokenEntity> getTokenEntityObservable() {
        return mTokenEntityBehaviorSubject;
    }
}
