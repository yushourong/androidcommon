/**
 * Project Name:pg2
 * Create User:yusr
 * Update User:yusr
 * Package Name:cn.cloudwalk.pg2
 * Date:2016/9/2711:39
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */
package cn.cloudwalk;/**
 * Create User:yusr
 * Date:2016/9/2711:39
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 *
 */

import android.content.Intent;
import android.os.Bundle;

import cn.cloudwalk.base.ActivityManager;
import cn.cloudwalk.base.BaseActivity;
import cn.cloudwalk.base.BaseFragment;


/**
 * ClassName: AppActivity <br/>
 * Description: TODO Description. <br/>
 * date: 2016/9/27 11:39<br/>
 *
 * @author 284891377
 * @version v1.0.0
 * @since JDK 1.7
 */
public  abstract  class AppActivity extends BaseActivity {

//Fragment的使用
//Step 1 避免重复添加
//Step 2 actvity的自动管理

    //获取第一个fragment
    protected abstract BaseFragment getFirstFragment();
    protected abstract int getContentViewId();
    //获取Intent
    protected void handleIntent(Intent intent) {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        if (null != getIntent()) {
            handleIntent(getIntent());
        }

        //避免重复添加Fragment
        if (null == getSupportFragmentManager().getFragments()) {
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }

    ActivityManager.getInstance().addAct(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().finishAct(this);
    }
}