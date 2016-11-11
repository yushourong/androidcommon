/**
 * Project Name:pg2
 * Create User:yusr
 * Update User:yusr
 * Package Name:cn.cloudwalk.pg2.base
 * Date:2016/9/2711:26
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */
package cn.cloudwalk.base;/**
 * Create User:yusr
 * Date:2016/9/2711:26
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 *
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * ClassName: BaseFragment <br/>
 * Description: TODO Description. <br/>
 * date: 2016/9/27 11:26<br/>
 *
 * @author 284891377
 * @version v1.0.0
 * @since JDK 1.7
 */
public abstract class BaseFragment extends Fragment {
    protected BaseActivity mBase;
    //step1 添加fragment
    //step2 移除fragment
    //step3  获取当前act
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mBase = (BaseActivity) activity;
    }
    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (null != fragment) {
            mBase.addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        mBase.removeFragment();
    }

    //----------------------------
    protected abstract void initView(View view, Bundle savedInstanceState);

    //获取fragment布局文件ID
    protected abstract int getLayoutId();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view, savedInstanceState);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}