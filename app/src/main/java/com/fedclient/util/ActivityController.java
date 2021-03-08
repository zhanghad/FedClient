package com.fedclient.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityController {
    public static List<Activity> activitys = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activitys.add(activity);//把传入的Activity添加到List中
    }

    public static void removeActivity(Activity activity) {
        activitys.remove(activity);//根据传入的Activity来删除
    }

    public static void finishAll() {
        for (Activity activity : activitys) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 除了传来的Activity其他的全部删除
     */
    public static void finishAll(Class<?>... clazz) {
        boolean isExist = false;
        for (Activity act : activitys) {
            for (Class c : clazz) {
                if (act.getClass().isAssignableFrom(c)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                if (!act.isFinishing()) {
                    act.finish();
                }
            } else {
                isExist = false;
            }
        }
    }

    /**
     * 从Activity集合查询,传入的Activity是否存在
     */
    public static Activity getActivity(Class<?> activity) {
        for (int i = 0; i < activitys.size(); i++) {
            // 判断是否是自身或者子类
            if (activitys.get(i).getClass().isAssignableFrom(activity)) {
                return activitys.get(i);
            }
        }
        return null;
    }

    public static void clearAcache(){
        for(Activity activity:activitys){
            ACache acache = ACache.get(activity);
            acache.clear();
        }
    }
}
