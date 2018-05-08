package com.wkw.uiframework.base.mvp.action;

import com.vongihealth.network.handler.RxErrorHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GoGo on 2018-4-25.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public class ViewActionQueueProviderImpl implements ViewActionQueueProvider {
    private final Map<String, ViewActionQueue> viewActionQueueMap = new HashMap<>();
    private final RxErrorHandler mRxErrorHandler;

    public ViewActionQueueProviderImpl(RxErrorHandler rxErrorHandler) {
        this.mRxErrorHandler = rxErrorHandler;
    }


    @Override
    public ViewActionQueue queueFor(final String queueId) {
        final ViewActionQueue viewActionQueue = viewActionQueueMap.get(queueId);
        if (viewActionQueue != null) {
            return viewActionQueue;
        }

        final ViewActionQueue newQueue = new ViewActionQueueImpl(mRxErrorHandler);
        viewActionQueueMap.put(queueId, newQueue);
        return newQueue;
    }

    @Override
    public void dispose(final String queueId) {
        viewActionQueueMap.remove(queueId);
    }
}
