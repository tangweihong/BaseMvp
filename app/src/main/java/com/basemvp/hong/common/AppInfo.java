package com.basemvp.hong.common;

import android.content.Context;
import android.os.Build;


public class AppInfo {
    public static String PACKAGE;
    public static String VERSION_NAME;
    public static int VERSION_CODE;
    public static String CHANNEL;
    public static String IMEI;
    public static String UUID;
    public static String HOST;
    public static String ANDROID_ID;
    public static String DEVICE_ID;
    public static String MANUFACTURER;
    public static String MODEL;
    public static String appId;

    public static void init(Context context) {
        PACKAGE = context.getPackageName();
//        VERSION_CODE = AppUtil.getVersionCode(context);
//        VERSION_NAME = AppUtil.getVersionName(context);
//        HOST = AppUtil.getMetaData(context, "HOST");
//        if (BuildConfig.DEBUG) {
//            SPUtil.init(context);
////            HOST = SPUtil.getInstance().get(SPUtil.Key.HOST, "ms.hashwang.com/");//正式环境
//            HOST = SPUtil.getInstance().get(SPUtil.Key.HOST, "172.17.1.200:8090/");//测试环境
//        }
//        IMEI = AppUtil.getDeviceId(context);
//        UUID = AppUtil.getUUID(context);
        MANUFACTURER = Build.MANUFACTURER;
        MODEL = Build.MODEL;
//        ANDROID_ID = AppUtil.getAndroidId(context);
//        DEVICE_ID = AppUtil.getDeviceId(context);
        ANDROID_ID = ANDROID_ID == null ? "" : ANDROID_ID;
        UUID = UUID == null ? "" : UUID;
        IMEI = IMEI == null ? "" : IMEI;
        appId = "hsw5d033cdf98fed";
//        appId = "1970010100000000";
    }
}