
/** Project Name:common
 * Create User:yusr
 * Update User:yusr
 * Package Name:cn.cloudwalk.common
 * Date:2016/9/2713:43
 * Copyright @ 2010-2016 Cloudwalk Information Technology Co.Ltd All Rights Reserved.
 */
package cn.cloudwalk;


/**
 * ClassName: Config <br/>
 * Description: TODO Description. <br/>
 * date: 2016/9/27 13:43<br/>
 *
 * @author 284891377
 * @version v1.0.0
 * @since JDK 1.7
 */
public class Config {
    /**okhttp超时**/
    public static final long httpConnectTimeout = 10000L;
    public static final long httpReadTimeout = 10000L;
    public static final long httpWriteTimeout = 10000L;
    /**是否调试模式**/
    public static final boolean isDebug = BuildConfig.DEBUG;
//
//    public static final String catchdir = Util.getDiskCacheDir(MyApplication.getIntstance()) + "/girls";
}