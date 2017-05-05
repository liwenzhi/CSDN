package com.example.comment.app;

import android.app.Application;


/**
 * 程序入口，做一些必要的初始化
 */

public class App extends Application {

    private static App sInstance;


    /**
     * 全局获取单例
     */
    public static App getInstance() {
        return sInstance;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }


}
