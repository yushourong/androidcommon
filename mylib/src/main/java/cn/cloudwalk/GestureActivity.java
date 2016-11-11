/**
 * Project Name:pg2
 * Create User:yusr
 * Update User:yusr
 * Package Name:cn.cloudwalk.pg2
 * Date:2016/9/2712:30
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */
package cn.cloudwalk;/**
 * Create User:yusr
 * Date:2016/9/2712:30
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * ClassName: GestureActivity <br/>
 * Description: TODO Description. <br/>
 * date: 2016/9/27 12:30<br/>
 *
 * @author 284891377
 * @version v1.0.0
 * @since JDK 1.7
 */
public abstract class GestureActivity extends AppActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private GestureDetector mGestureDetector;
    private int verticalMinDistance = 300;
    private int minVelocity = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGesture();

    }

    private void initGesture() {
        mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener) this);
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if (e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
            //向左手势

        } else if (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
            //向右手势
            doFinish();
        }

        return false;
    }

    protected abstract void doFinish();


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        mGestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }
}