package com.wkw.uiframework;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by wukewei on 2017/8/28.
 */

public class AppManager {
    private static Stack<Activity> activityStack = new Stack<>();
    private static AppManager mInstance;

    private AppManager() {
    }

    public static AppManager getAppManager() {
        if (mInstance == null) {
            synchronized (AppManager.class) {
                if (mInstance == null) {
                    mInstance = new AppManager();
                }
            }
        }
        return mInstance;
    }

    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
    }


    public Activity curremtActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }


    public void finishActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity) && !activity.isFinishing()) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

}
