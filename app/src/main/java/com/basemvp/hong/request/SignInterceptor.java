
package com.basemvp.hong.request;

import android.text.TextUtils;

import com.basemvp.hong.common.AppInfo;
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
    MMKV mmkv;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        mmkv = MMKV.defaultMMKV();
        long now = System.currentTimeMillis() / 1000;
        Headers headers = originalRequest.headers().newBuilder()
                .add("Access-Token", "" + mmkv.decodeString(IntentExtra.TOKEN, ""))
                .build();
        RequestBody requestBody = originalRequest.body();
        RequestBody newRequestBody;
        Map<String, String> map = new HashMap<>();
        if (originalRequest.method().equals("GET")) {
            HttpUrl httpUrl = originalRequest.url()
                    .newBuilder()
                    .addQueryParameter("timestamp", now + "")//平台号
                    .addQueryParameter("app_id", AppInfo.appId + "")//版本号
                    .build();
            //添加签名
            Set<String> nameSet = httpUrl.queryParameterNames();
            ArrayList<String> nameList = new ArrayList<>();
            nameList.addAll(nameSet);
            Map<String, Object> paramMap = new HashMap<>();
            for (int i = 0; i < nameList.size(); i++) {
                paramMap.put(nameList.get(i), httpUrl.queryParameterValue(i));
                map.put(nameList.get(i), httpUrl.queryParameterValue(i));
                XLog.e("code:", ""+nameList.get(i)+"---"+httpUrl.queryParameterValue(i));
            }
            httpUrl = httpUrl.newBuilder().addQueryParameter("sign", RequestUtils.createLinkString(map).toUpperCase()).build();
            originalRequest = originalRequest.newBuilder().url(httpUrl).build();
        } else if (originalRequest.method().equals("POST")) {
            if (requestBody instanceof FormBody) {
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                FormBody formBody = (FormBody) requestBody;
                for (int i = 0; i < formBody.size(); i++) {
                    String name = formBody.name(i);
                    String value = formBody.value(i);
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(value)) {
                        formBodyBuilder.add(name, value);
                        map.put(name, value);
                    }
                }
                formBodyBuilder.add("timestamp", now + "");
                map.put("timestamp", now + "");
                formBodyBuilder.add("app_id", AppInfo.appId + "");
                map.put("app_id", AppInfo.appId + "");
                formBodyBuilder.add("sign", RequestUtils.createLinkString(map).toUpperCase());
                newRequestBody = formBodyBuilder.build();
            } else if (requestBody instanceof MultipartBody) {
                MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
                MultipartBody multipartBody = (MultipartBody) requestBody;
                for (int i = 0; i < multipartBody.size(); i++) {
                    MultipartBody.Part part = multipartBody.part(i);
                    String value = RequestBodyUtil.getPartTextValue(part);
                    String name = RequestBodyUtil.getPartName(part);
                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(value)) {
                        map.put(name, value);
                    }
                    multipartBodyBuilder.addPart(part);
                }
                multipartBodyBuilder.addPart(RequestBodyUtil.getPart("sign", RequestUtils.createLinkString(map)));
                newRequestBody = multipartBodyBuilder.setType(MediaType.parse("multipart/form-data")).build();
            } else {
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                formBodyBuilder.add("timestamp", now + "");
                map.put("timestamp", now + "");
                formBodyBuilder.add("app_id", AppInfo.appId + "");
                map.put("app_id", AppInfo.appId + "");
                formBodyBuilder.add("sign", RequestUtils.createLinkString(map).toUpperCase());
                newRequestBody = formBodyBuilder.build();
            }
            originalRequest = originalRequest
                    .newBuilder()
                    .method(originalRequest.method(), newRequestBody)
                    .headers(headers)
                    .build();
        }
        return chain.proceed(originalRequest);
    }
}
