package com.basemvp.hong.common;


import com.basemvp.hong.BuildConfig;

/**
 * Constants 接口加密秘钥
 * Created by Hong on 2019/8/14.
 * Version:1
 */
public class Constants {
    public static final String HOME_FILE_PATH = "Hash";
    public static final String _HTTP = "http://";
    public static final String _HTTPS = "https://";//正式环境使用
    public static final String IMG_BASE = "";
    public static String _URL = _HTTP + AppInfo.HOST;
    //    public static String APP_SECRET = "3eeb03e5da05c866e3d3d1b261e4c6f0";
    public static String APP_SECRET = BuildConfig.DEBUG ? "6c903ad5ca8fb80aac96630da2992ea6" : "3eeb03e5da05c866e3d3d1b261e4c6f0";

}
