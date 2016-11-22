package cn.cloudwalk.common.websocket;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Jing on 16/11/22.
 */

public class websocketUtil {
    private URI address = null;
    public static WebSocketClient client;
    /**
     * 使用websocket进行陌生人报警注册事件
     */
    public void connectStranger(String picUrl, final String videoNum){//传递了地址和摄像头编号

        String webUrl = "ws://" + picUrl + ":2016";//服务器地址
        try {
            address = new URI(webUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (address!=null) {
            client = new WebSocketClient(address,new Draft_17()) {//生成客户端实例

                @Override
                public void onOpen(ServerHandshake serverHandshake) {//打开长链接
                    Log.i("tag","连接到服务器");
                    JSONObject json = new JSONObject();
                    try {
                        json.put("action","STRANGER");
                        json.put("videoSourceNumber",videoNum);
                        client.send(json.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onMessage(String s) {
                    Log.i("tag",s);

                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.i("tag","关闭服务器连接");
                }

                @Override
                public void onError(Exception e) {

                }
            };
        }
    }
}
