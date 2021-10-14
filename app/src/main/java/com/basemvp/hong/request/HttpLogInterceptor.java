package com.basemvp.hong.request;





import com.basemvp.hong.BuildConfig;
import com.basemvp.hong.utils.XLog;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by hong on 2020/5/9 9:48.
 */
public class HttpLogInterceptor implements HttpLoggingInterceptor.Logger {
    @Override
    public void log(String message) {
        if (BuildConfig.DEBUG) {
            XLog.i("HTTP请求：" + message);
        }
    }
}
