package com.basemvp.hong;

/**
 * Create by Hong on 2020/4/15 10:31.
 */

import android.app.Activity;

import java.util.Stack;

/**
 * Activity 管理类
 */
public class ActivityManager {

    private Stack<Activity> activityStack;

    private ActivityManager() {

    }

    public static final ActivityManager getInstance() {
        return ActivityManagerHolder.instance;
    }

    private static class ActivityManagerHolder {
        private static final ActivityManager instance = new ActivityManager();
    }

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (activityStack != null) {
            activityStack.remove(activity);
        }
    }

    public Activity getActivity(Class<?> clazz) {
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                if (clazz.equals(activity.getClass())) {
                    return activity;
                }
            }
        }
        return null;
    }

    public Activity getCurrentActivity() {
        if (activityStack != null) {
            return activityStack.lastElement();
        }
        return null;
    }

    /**
     * 获取所有 Activity
     */
    public Stack<Activity> getAllActivityStacks() {
        return activityStack;
    }

    /**
     * 结束指定的 Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            if (!activity.isFinishing()) {
                activity.finish();
                activityStack.remove(activity);
            }
        }
    }

    /**
     * 结束指定类名的 Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束除当前传入以外所有 Activity
     */
    public void finishOthersActivity(Class<?> cls) {
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                if (!activity.getClass().equals(cls)) {
                    activity.finish();
                }
            }
        }
    }

    /**
     * 结束所有 Activity
     */
    public void finishAllActivity() {
        if (activityStack != null) {
            for (Activity activity : activityStack) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出 app 时调用
     */
    public void exitApp() {
        try {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
