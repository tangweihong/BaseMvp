package com.basemvp.hong.common;

import android.content.Context;
import android.os.Build;

import com.basemvp.hong.BuildConfig;
import com.basemvp.hong.utils.AppUtil;


public class AppInfo {
    public static String PACKAGE;
    public static String VERSION_NAME;
    public static int VERSION_CODE;
    public static String HOST;
    public static String appId;

    public static void init(Context context) {
        PACKAGE = context.getPackageName();
        VERSION_CODE = AppUtil.getVersionCode(context);
        VERSION_NAME = AppUtil.getVersionName(context);
//        HOST = AppUtil.getMetaData(context, "HOST");
//        HOST = "";//正式环境
        HOST = "";//测试环境
        if (BuildConfig.DEBUG) {
//        HOST = "";//正式环境
            HOST = "";//测试环境
        }
    }
}