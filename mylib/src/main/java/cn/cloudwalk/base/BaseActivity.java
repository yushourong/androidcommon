/**
 * Project Name:pg2
 * Create User:yusr
 * Update User:yusr
 * Package Name:cn.cloudwalk.base
 * Date:2016/9/2711:21
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */
package cn.cloudwalk.base;/**
 * Create User:yusr
 * Date:2016/9/2711:21
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import com.litesuits.common.assist.Toastor;
import com.litesuits.common.utils.DialogUtil;

import butterknife.ButterKnife;
import cn.cloudwalk.libproject.progressHUD.CwProgressHUD;


/**
 * ClassName: BaseActivity <br/>
 * Description: TODO Description. <br/>
 * date: 2016/9/27 11:21<br/>
 *
 * @author 284891377
 * @version v1.0.0
 * @since JDK 1.7
 */
public abstract class BaseActivity extends AppCompatActivity {
    //step1 添加fragment
    //step2 移除fragment
    //step3 返回键处理
    //step4 progressDialog的统一处理
    /**进度框**/
    CwProgressHUD processDialog;
    /**提示框**/
    Dialog mDialog;
    Toastor mToastor;

    //step5 toolbar使用
    protected Toolbar toolbar;

    //TODO  这三个方法的必要性

    /**
     * 布局中Fragment的ID
     * @return
     */
    protected abstract int getFragmentContentId();

    protected abstract void initView();

    protected abstract void initEvent();

    protected abstract void initData();

    /**
     *setContentView里完成 initView() initEvent() initData()
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initView();
        initEvent();
        initData();
    }

    /**
     * show toolbar
     * if the title is set ,the title will show else it will show the label set to the activity
     *
     * @param title
     */
    protected void showToolbar(int toolbarResid, String title, int titleColorRes, int backIconRes) {
        toolbar = (Toolbar) findViewById(toolbarResid);
        if (toolbar != null) {
            if (title != null) {
                toolbar.setTitle(title);
            }
            toolbar.setTitleTextColor(getResources().getColor(titleColorRes));
            toolbar.showOverflowMenu();
            setSupportActionBar(toolbar);

            if (showNavigationIcon()) {
                //toolbar.setNavigationIcon(R.mipmap.toolbar_back);
                toolbar.setNavigationIcon(backIconRes);

                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });
            }
        }
    }

    /**
     * set true to show navigation icon
     *
     * @return
     */
    protected boolean showNavigationIcon() {
        return true;
    }

    //添加fragment
    protected void addFragment(cn.cloudwalk.base.BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    //移除fragment
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (processDialog != null && processDialog.isShowing())
            processDialog.dismiss();
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }


    //对话框----------------------------
    public void showTips(Context context, String title, String des) {
        mDialog = DialogUtil.showTips(context, title, des, null, null);

    }

    public void showTips(Context context, int title, int des) {
        mDialog = DialogUtil.showTips(context, context.getString(title), context.getString(des));
    }

    public void showTips(Context context, int title, int des, int btn, DialogInterface.OnDismissListener dismissListener) {
        mDialog = DialogUtil.showTips(context, context.getString(title), context.getString(des), context.getString(btn), dismissListener);
    }

    //进度框----------------------------
    public void showProcessDialog(String lable) {
        processDialog = CwProgressHUD.create(this).setStyle(CwProgressHUD.Style.SPIN_INDETERMINATE).setLabel(lable)
                .setCancellable(false).setAnimationSpeed(2).setDimAmount(0.5f);
    }
    //toast------------------------------

    /**
     * toast显示 Toast.LENGTH_SHORT
     * @param text
     */
    public void showSingletonToast(String text) {
        if (mToastor == null) mToastor = new Toastor(this);
        mToastor.getSingletonToast(text).show();
    }

    /**
     * toast显示 Toast.LENGTH_SHORT
     * @param text
     */
    public void showSingleLongToast(String text) {
        if (mToastor == null) mToastor = new Toastor(this);
        mToastor.getSingleLongToast(text).show();
    }

    /**
     * toast显示 Toast.LENGTH_SHORT
     * @param resId
     */
    public void showSingletonToast(int resId) {
        if (mToastor == null) mToastor = new Toastor(this);
        mToastor.getSingletonToast(resId).show();
    }

    /**
     * toast显示 Toast.LENGTH_SHORT
     * @param resId
     */
    public void showSingleLongToast(int resId) {
        if (mToastor == null) mToastor = new Toastor(this);
        mToastor.getSingleLongToast(resId).show();
    }
    //类之间跳转(动画)
}