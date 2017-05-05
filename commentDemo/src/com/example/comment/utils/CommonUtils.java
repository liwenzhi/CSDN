package com.example.comment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * 通用工具类
 */

public class CommonUtils {

    private static final String TAG = "CommonUtils-TAG";

    /**
     * toast some msg
     */
    public static void showToast(Context context, String msg) {

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * check if network avalable
     */
    public static boolean isNetWorkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable() && mNetworkInfo.isConnected();
            }
        }

        return false;
    }

    /**
     * check if sdcard exist
     */
    public static boolean isSdcardExist() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 拆箱整形对象，如果为空返回 0
     */
    public static int getIntegerValue(Integer integer) {
        return integer == null ? 0 : integer;
    }

    /**
     * Log
     */
    //这里DEBUG的作用是，可以在程序完成后设置DEBUG的值为false，程序以后就不会在显示以前的打印信息
    public static boolean DEBUG = true;

    //各种Log打印
    public static void e(Object o) {
        if (DEBUG)
            Log.e(TAG, "打印：------      " + o.toString());
    }

    public static void e(int i) {
        if (DEBUG)
            Log.e(TAG, "打印：------      " + i);
    }

    public static void e(float i) {
        if (DEBUG)
            Log.e(TAG, "打印：------      " + i);
    }

    public static void e(boolean b) {
        if (DEBUG)
            Log.e(TAG, "打印：------      " + b);
    }

}
