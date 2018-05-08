package com.wkw.uiframework.base.mvp.action;

/**
 * Created by GoGo on 2018-4-25.
 * Email zjwkw1992@163.com
 * GitHub https://github.com/zj-wukewei
 */

public interface ViewActionQueueProvider {
    ViewActionQueue queueFor(String queueId);

    void dispose(String queueId);
}
