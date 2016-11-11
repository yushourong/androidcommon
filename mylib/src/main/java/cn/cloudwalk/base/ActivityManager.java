/**
 * Project Name:pg2
 * Create User:yusr
 * Update User:yusr
 * Package Name:cn.cloudwalk.pg2.base
 * Date:2016/9/2710:50
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */
package cn.cloudwalk.base;/**
 * Create User:yusr
 * Date:2016/9/2710:50
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * ClassName: ActivityManager <br/>
 * Description: TODO Description. <br/>
 * date: 2016/9/27 10:50<br/>
 *
 * @author 284891377
 * @version v1.0.0
 * @since JDK 1.7
 */
public class ActivityManager {

    private static Stack<Activity> activityStack;

    static {
        instance = new ActivityManager();
    }

    public static ActivityManager getInstance() {
        return instance;
    }

    private static ActivityManager instance;

    //添加
    public void addAct(Activity act) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(act);

    }

    //取出
    public Activity getCurrent() {
        Activity act = activityStack.lastElement();
        return act;
    }

    //结束当前
    public void finishCurrent() {
        Activity act = activityStack.lastElement();
        finishAct(act);
    }


    //结束指定
    public void finishAct(Activity act) {
        if (act != null) {
            activityStack.removeElement(act);
            act.finish();
            act = null;
        }
    }

    //结束指定class的最近实例
    public void finishAct(Class<?> cls) {
        Activity lastAct = null;
        for (Activity act : activityStack) {
            if (act.getClass().equals(cls)) {
                lastAct = act;

            }
        }
        if (lastAct != null) {
            finishAct(lastAct);
        }
    }

    //结束指定class的所有
    public void finishAllAct(Class<?> cls) {

        for (Activity act : activityStack) {
            if (act.getClass().equals(cls)) {
                finishAct(act);

            }
        }

    }

    //结束全部
    public void finishAllAct() {

        for (Activity act : activityStack) {
            finishAct(act);
        }
        activityStack.clear();
    }

    //退出应用
    public void exitApp(Context context) {
        try {

            finishAllAct();
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {

        }

    }
}


