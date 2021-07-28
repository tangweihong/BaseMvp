package com.basemvp.hong.utils;

import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hong on 2020/4/10 16:45.
 */
public class AppUtil {
    private static TelephonyManager tm;

    /**
     * 获取当前运行的进程名
     */
    public static String getProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }

        return null;
    }

    /**
     * 获取当前运行的所有进程名
     */
    public static List<String> getProcessName(Context context, String packageName) {
        List<String> list = new ArrayList<String>();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.processName.startsWith(packageName)) {
                // System.out.println("p:"+appProcess.processName);
                list.add(appProcess.processName);
            }
        }
        return list;
    }


    /**
     * 实现文本复制功能
     */
    @SuppressWarnings("deprecation")
    public static void copy(Context context, String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        // 得到剪贴板管理器
//        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//        cmb.setText(content.trim());
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建一个剪贴数据集，包含一个普通文本数据条目（需要复制的数据）,其他的还有
        // newHtmlText、
        // newIntent、
        // newUri、
        // newRawUri
        ClipData clipData = ClipData.newPlainText(null, content);

        // 把数据集设置（复制）到剪贴板
        clipboard.setPrimaryClip(clipData);
    }

    /**
     * 实现粘贴功能
     */
    @SuppressWarnings("deprecation")
    public static String paste(Context context) {
        // 得到剪贴板管理器
//        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//        return cmb.getText().toString().trim();
        // 获取系统剪贴板
        CharSequence text = "";
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

        // 获取剪贴板的剪贴数据集
        ClipData clipData = clipboard.getPrimaryClip();

        if (clipData != null && clipData.getItemCount() > 0) {
            // 从数据集中获取（粘贴）第一条文本数据
            text = clipData.getItemAt(0).getText();
        }
        return TextUtils.isEmpty(text) ? "" : text.toString();
    }

    /**
     * 获取当前应用的versionCode
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 获取当前应用的versionName
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取Manifest里面配置的渠道版本
     * <p>
     * 2014-11-14
     * </p>
     */
    public static String getMetaData(Context context, String name) {
        String metaData = "";
        try {
            metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData.getString(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metaData;
    }


    public static String getMac(Context context) {
        SharedPreferences config = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String macSerial = config.getString("_mac", null);
        if (macSerial != null) {
            return macSerial;
        }
        String str = "";

        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        if (macSerial != null) {
            config.edit().putString("_mac", macSerial).apply();
        }
        if (macSerial == null) {
            macSerial = "xxxxxx";
        }
        return macSerial;
    }

    public static String getAndroidId(Context context) {
        return android.provider.Settings.Secure.getString(context.getApplicationContext().getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
    }


    /**
     * dp to px
     */
    public static int dp2px(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * px to dp
     */
    public static int px2dp(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * sp to px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * screen width
     */
    public static int screenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * screen height
     */
    public static int screenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是字母
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        String regex = "[a-zA-Z]+";
        Matcher m = Pattern.compile(regex).matcher(str);
        return m.matches();
    }

    //邮箱验证
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return strEmail.matches(strPattern);
        }
    }

    /**
     * 处理返回数据为空的数字，默认为0
     *
     * @param string
     * @return
     */
    public static String TextUtilsNumEmpty(String string) {
        if (TextUtils.isEmpty(string)) {
            return "0";
        } else {
            return string;
        }
    }

    /**
     * 格式化 数字金额  NULL值默认0  小数位 默认截断
     *
     * @param num      数字
     * @param newScale 保留小数位数
     * @return
     */
    public static BigDecimal BigDecimalText(String num, int newScale) {
        BigDecimal scale = new BigDecimal(TextUtilsNumEmpty(num)).setScale(newScale, BigDecimal.ROUND_DOWN);
        return BigDecimal.valueOf(scale.doubleValue());
    }
    private static long lastClickTime = 0;
    private static long DIFF = 1000;
    private static int lastButtonId = -1;
    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(-1, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击
     *
     * @return
     */
    public static boolean isFastDoubleClick(int buttonId) {
        return isFastDoubleClick(buttonId, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击
     *
     * @param diff
     * @return
     */
    public static boolean isFastDoubleClick(int buttonId, long diff) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff) {
            Log.v("isFastDoubleClick", "短时间内按钮多次触发");
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }

    public static String getHtmlFontText(String color, String text) {
        String mText = "<font color=\"" + color + "\">" + text + "</font>";
        return mText;
    }

    public static <E> boolean isNotEmpty(List<E> list) {
        return list != null && !list.isEmpty();
    }

    public static <E> boolean isNotEmpty(E[] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        for (E e : array) {
            if (e != null) {
                return true;
            }
        }
        return false;
    }

}
