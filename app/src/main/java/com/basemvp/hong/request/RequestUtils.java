
package com.basemvp.hong.request;

import android.text.TextUtils;

import com.basemvp.hong.common.Constants;
import com.basemvp.hong.utils.DESUtil;
import com.basemvp.hong.utils.EncryptUtils;
import com.basemvp.hong.utils.XLog;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * RequestUtils
 * Created by Lokiy on 2016/10/8.
 * Version:1
 */
class RequestUtils {
    private final static DecimalFormat df = new DecimalFormat("00");

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param map 需要排序并参与字符拼接的参数组
     * @param
     * @return 拼接后字符串
     */
    static String createLinkString(Map<String, String> map) {
        Map<String, String> signParameterMap = new HashMap<>(map);
        List<String> keys = new ArrayList<>(signParameterMap.keySet());
        Collections.sort(keys);
        String sign = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = signParameterMap.get(key);
            if (TextUtils.isEmpty(value)) {
                continue;
            }

//            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
//                sign = sign + key + "=" + value;
//            } else {
            sign = sign + key + "=" + value + "&";
//            }
        }
//        if (sign.endsWith("&")) {
//            sign = sign.substring(0, sign.length() - 1);
//        }
        sign = sign + "key=" + Constants.APP_SECRET;
		XLog.e("Task", "签名前：" + sign);


        return EncryptUtils.MD5(sign);
    }

    static String getEncryptToken(long now, String value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(now);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        String k = df.format(day) + df.format(hour) + df.format(minute) + df.format(second);
        //noinspection deprecation
//		XLog.e("Task", "加密Key：" + k);
        try {
            value = URLEncoder.encode(DESUtil.encryptWithoutEncode(value, k), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }
}
