package com.basemvp.hong.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


import com.basemvp.hong.App;
import com.basemvp.hong.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by hong on 2020/4/28 9:28.
 */
public class ToastUtils {


    private static Toast sCustomToast;

    public static void show(int resId) {
        show(App.getApp().getResources().getString(resId));
    }

    public static synchronized void show(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        showCustom(msg);
    }

    private static void showCustom(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
//        synchronized (DEFAULT_FORMAT_TEXT) {
        if (sCustomToast == null) {
            sCustomToast = new Toast(App.getApp());
            sCustomToast.setGravity(Gravity.CENTER, 0, 0);
            @SuppressLint("InflateParams") View view = LayoutInflater.from(App.getApp()).inflate(R.layout.dialog_toast, null);
            sCustomToast.setView(view);
        } else {
            sCustomToast.cancel();
            sCustomToast = new Toast(App.getApp());
            sCustomToast.setGravity(Gravity.CENTER, 0, 0);
            @SuppressLint("InflateParams") View view = LayoutInflater.from(App.getApp()).inflate(R.layout.dialog_toast, null);
            sCustomToast.setView(view);
        }

        if (msg.length() > 15) {
            sCustomToast.setDuration(Toast.LENGTH_LONG);
        } else {
            sCustomToast.setDuration(Toast.LENGTH_SHORT);
        }
        sCustomToast.setText(msg);
        sCustomToast.show();
    }




}