package com.basemvp.hong;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.webkit.WebView;

import com.basemvp.hong.common.AppInfo;
import com.basemvp.hong.common.Constants;
import com.basemvp.hong.utils.XLog;
import com.tencent.mmkv.MMKV;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDex;

/**
 * Created by hong on 2020/6/5 20:13.
 */
public class App extends Application {

    static App sContext;

    public static App getApp() {
        return sContext;
    }

    public static void onChangeLanguage() {
        getApp().initAppLanguage();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        AppInfo.init();
        MMKV.initialize(this);
        if (BuildConfig.DEBUG) {
            XLog.enableLogging();
        }
        registerActivityLifecycleCallbacks(lifecycleCallbacks);
        new WebView(this).destroy();
        initAppLanguage();
    }


    private void initAppLanguage(){
        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(MMKV.defaultMMKV().decodeString(Constants.LANGUAGE, "zh")));
        res.updateConfiguration(conf, res.getDisplayMetrics());
    }



    ActivityLifecycleCallbacks lifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            ActivityManager.getInstance().addActivity(activity);
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {

        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
            ActivityManager.getInstance().removeActivity(activity);
        }
    };
}
