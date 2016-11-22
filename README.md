通用功能模块:

1.websocket长连接请求使用

使用包java-websocket-1.3.0.jar

核心方法如下:

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

onOpen()中传递信息，onMessage()中返回信息.

client 实例化后就需要连接client.connect()。
