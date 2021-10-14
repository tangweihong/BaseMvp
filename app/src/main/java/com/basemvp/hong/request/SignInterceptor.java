
package com.basemvp.hong.request;

import android.text.TextUtils;

import com.basemvp.hong.common.AppInfo;
import com.basemvp.hong.common.Constants;
import com.basemvp.hong.common.IntentExtra;
import com.basemvp.hong.utils.XLog;
import com.tencent.mmkv.MMKV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * SignInterceptor
 * Created by Hong on 2019/7/8.
 * Version:1
 */
public class SignInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        MMKV mmkv = MMKV.defaultMMKV();
        Request originalRequest = chain.request();
        String lang;
        String token;
        if (mmkv.decodeString(Constants.LANGUAGE, "zh").equals("zh")) {
            lang = "zh-CN";
        } else {
            lang = "en";
        }
        token = mmkv.decodeString(Constants.TOKEN, "");
        Headers headers = originalRequest.headers().newBuilder()
                .add("Accept", "application/json")
                .add("Content-Type", "application/json")
                .add("Authorization", token)
                .add("language", lang)
                .build();
        Request request = originalRequest.newBuilder().headers(headers).build();
        return chain.proceed(request);
    }
}
