package com.basemvp.hong.common;

import android.content.Context;
import android.os.Build;

import com.basemvp.hong.BuildConfig;
import com.basemvp.hong.utils.AppUtil;


public class AppInfo {
    public static String HOST;
    public static String WS_HOST;
    public static String WEB_HOST;

    public static void init() {
        HOST = "https://www.wanandroid.com/";//线上正式环境
        WEB_HOST = "https://www.dragonai.pro/";//WebView
        //线上测试环境
        if (BuildConfig.DEBUG) {
            HOST = "https://www.wanandroid.com/";//测试

        }
    }
}