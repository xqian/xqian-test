package com.example.xinqian.clientlib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;

public class ClientHelper {

    private static boolean LOGGING_ENABLED;
    private static final int STACK_TRACE_LEVELS_UP;
    private static final String TagPrefix;

    static
    {
        TagPrefix = "xinqian1-";
        STACK_TRACE_LEVELS_UP = 5;
        LOGGING_ENABLED = true;
    }

    public static byte[] convertStringToBytes(String p_str) {
        return Base64.decode(p_str, Base64.DEFAULT);

    }

    public static String convertBytesToString(byte[] p_byte) {
        return Base64.encodeToString(p_byte, Base64.DEFAULT);
    }

    public static Boolean isConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        i("Helper", "isConnected:" + isConnected);
        return isConnected;
    }



    public static Boolean GetLoggingEnabled() {
        return LOGGING_ENABLED;
    }

    public static void v(String tag, String message) {
        if (LOGGING_ENABLED) {
            android.util.Log.v(TagPrefix + tag, getClassNameMethodNameAndLineNumber() + message);
        }
    }

    public static void d(String tag, String message) {
        if (LOGGING_ENABLED) {
            android.util.Log.d(TagPrefix + tag, getClassNameMethodNameAndLineNumber() + message);
        }
    }

    public static void i(String tag, String message) {
        if (LOGGING_ENABLED) {
            android.util.Log.i(TagPrefix + tag, getClassNameMethodNameAndLineNumber() + message);
        }
    }

    public static void w(String tag, String message) {
        if (LOGGING_ENABLED) {
            Log.w(TagPrefix + tag, getClassNameMethodNameAndLineNumber() + message);
        }
    }

    public static void e(String tag, String message) {
        if (LOGGING_ENABLED) {
            android.util.Log.e(TagPrefix + tag, getClassNameMethodNameAndLineNumber() + message);
        }
    }

    private static int getLineNumber() {
        return Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getLineNumber();
    }

    private static String getClassName() {
        String fileName =
                Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getFileName();

        // Removing ".java" and returning class name
        return fileName.substring(0, fileName.length() - 5);
    }

    private static String getMethodName() {
        return Thread.currentThread().getStackTrace()[STACK_TRACE_LEVELS_UP].getMethodName();
    }

    private static String getClassNameMethodNameAndLineNumber() {
        return "[" + getClassName() + "." + getMethodName() + "()-" + getLineNumber() + "]: ";
    }

}
