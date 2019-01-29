package com.vongihealth.network.download;

import com.vongihealth.network.interactor.DefaultObserver;

import java.io.File;

import okhttp3.ResponseBody;

/**
 * @author GoGo on 2019-01-29.
 */
public class DownLoadSubscriber extends DefaultObserver<ResponseBody> {
    private ProgressCallBack fileCallBack;

    public DownLoadSubscriber(ProgressCallBack fileCallBack) {
        this.fileCallBack = fileCallBack;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (fileCallBack != null)
            fileCallBack.onStart();
    }

    @Override
    public void onComplete() {
        if (fileCallBack != null)
            fileCallBack.onCompleted(new File(fileCallBack.getDestFileDir(), fileCallBack.getDestFileName()));
    }

    @Override
    public void onError(Throwable e) {
        if (fileCallBack != null)
            fileCallBack.onError(e);
    }

    @Override
    public void onNext(ResponseBody t) {

    }
}
