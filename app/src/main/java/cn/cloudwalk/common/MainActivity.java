package cn.cloudwalk.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.cloudwalk.common.websocket.websocketUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //websocket使用
        final websocketUtil ws = new websocketUtil();
        ws.connectStranger("","");//输入服务器ip与摄像头编号
        new Thread(new Runnable() {
            @Override
            public void run() {
                ws.client.connect();
            }
        }).start();
    }
}
