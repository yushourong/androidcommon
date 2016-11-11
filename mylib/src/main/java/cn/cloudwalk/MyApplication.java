/**
 * Project Name:pg2
 * Create User:yusr
 * Update User:yusr
 * Package Name:cn.cloudwalk.pg2.app
 * Date:2016/9/2713:38
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */
package cn.cloudwalk;/**
 * Create User:yusr
 * Date:2016/9/2713:38
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * ClassName: MyApplication <br/>
 * Description: TODO Description. <br/>
 * date: 2016/9/27 13:38<br/>
 *
 * @author 284891377
 * @version v1.0.0
 * @since JDK 1.7
 */
public class MyApplication extends Application {
    //Step1 调试开关
    //step 2异常处理
    //step 3 配置http
    static MyApplication myApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        //异常处理
        //okhttp3配置,不使用http可以去掉
        OkHttpClient.Builder builder=new OkHttpClient.Builder()
                .connectTimeout(Config.httpConnectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(Config.httpReadTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(Config.httpWriteTimeout, TimeUnit.MILLISECONDS);
        if(Config.isDebug)builder.addInterceptor(new LoggerInterceptor("TAG"));//添加日志tag
        OkHttpClient okHttpClient =builder.build();
        OkHttpUtils.initClient(okHttpClient);

    }



    public static MyApplication getIntstance() {
        return myApplication;
    }
}