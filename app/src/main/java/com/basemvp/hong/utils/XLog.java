package com.basemvp.hong.utils;

import android.util.Log;

public final class XLog {
    private static final String LOG_FORMAT = "%1$s\n%2$s";
    private static volatile boolean DEBUG = false;
    private static final String TAG = "hash %s";

    private XLog() {
    }

    /**
     * Enables logger (if {@link #disableLogging()} was called before)
     */
    public static void enableLogging() {
        DEBUG = true;
    }

    /**
     * is Enable logger
     */
    public static boolean isLogging() {
        return DEBUG;
    }

    /**
     * Disables logger, no logs will be passed to LogCat, all log methods will do nothing
     */
    public static void disableLogging() {
        DEBUG = false;
    }

    public static void v(String tag, String message, Object... args) {
        log(tag, Log.VERBOSE, null, message, args);
    }

    public static void d(String tag, String message, Object... args) {
        log(tag, Log.DEBUG, null, message, args);
    }

    public static void i(String message, Object... args) {
        log(TAG, Log.INFO, null, message, args);
    }

    public static void i(String tag, String message, Object... args) {
        log(tag, Log.INFO, null, message, args);
    }

    public static void w(String tag, String message, Object... args) {
        log(tag, Log.WARN, null, message, args);
    }

    public static void w(String tag, Throwable ex) {
        log(tag, Log.WARN, ex, ex == null ? "" : ex.toString());
    }

    public static void e(String tag, Throwable ex) {
        log(tag, Log.ERROR, ex, ex == null ? "" : ex.toString());
    }

    public static void e(String message, Object... args) {
        log(TAG, Log.ERROR, null, message, args);
    }

    public static void e(String tag, String message, Object... args) {
        log(tag, Log.ERROR, null, message, args);
    }

    public static void e(String tag, Throwable ex, String message, Object... args) {
        log(tag, Log.ERROR, ex, message, args);
    }

    public static void start(String tag) {
        v(tag, "");
        v(tag, "------------START--------------");
    }

    public static void start(String tag, String message) {
        v(tag, "");
        v(tag, "------------START " + message + "--------------");
    }

    public static void end(String tag) {
        v(tag, "-------------END---------------");
        v(tag, "");
    }

    public static void end(String tag, String message) {
        v(tag, "-------------END " + message + "---------------");
        v(tag, "");
    }

    private static void log(String tag, int priority, Throwable ex, String message, Object... args) {
        if (!DEBUG) return;

        if (args != null && args.length > 0) {
            message = String.format(message, args);
        }

        String log;
        if (ex == null) {
            log = message;
        } else {
            String logMessage = message == null ? ex.getMessage() : message;
            String logBody = Log.getStackTraceString(ex);
            log = String.format(LOG_FORMAT, logMessage, logBody);
        }
        if (tag == null) {
            tag = "XLog";
        }
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (log.length() > max_str_length) {
//            Log.i(tag, message.substring(0, max_str_length));
            Log.println(priority, tag, "X-LOG     " + log.substring(0, max_str_length));
            log = log.substring(max_str_length);
        }
        //剩余部分
//        Log.i(tag, msg);
        Log.println(priority, tag, "X-LOG     " + log);

//        Log.println(priority, tag, "X-LOG     " + log);
    }
}