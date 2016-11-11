package cn.cloudwalk.util;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

/**
 * Created by Administrator on 2016/10/24 0024.
 */

public class IntentUtils {
 /**
  * openBrowser:打开浏览器. <br/>
  * @author:284891377   Date: 2016/10/24 0024 16:36
  *
  * @since JDK 1.7
  */
    public static boolean openBrowser(Context context, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        try {
            context.startActivity(i);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }
    /**
     * share:分享文字. <br/>
     * @author:284891377   Date: 2016/10/24 0024 16:36
     *
     * @since JDK 1.7
     */
    public static boolean share(Context context, String text,String  title) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        try {
            context.startActivity(Intent.createChooser(shareIntent, title));
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }
    /**
     * download:使用系统下载器,下载某个文件. <br/>
     * @author:284891377   Date: 2016/10/24 0024 16:36
     *
     * @since JDK 1.7
     */
    public static void download(Context context, String fileName, String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        // This put the download in the same Download dir the browser uses
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

        // When downloading music and videos they will be listed in the player
        // (Seems to be available since Honeycomb only)
        request.allowScanningByMediaScanner();

        // Notify user when download is completed
        // (Seems to be available since Honeycomb only)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        // Start download
        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        dm.enqueue(request);
    }
}
