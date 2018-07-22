package com.dfwr.zhuanke.zhuanke.wechatshare;

import android.os.Looper;

public class StringUtils {
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)) {
            return true;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static String currentThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return "主线程";
        }
        return "子线程";
    }

    public static int toInt(String str, int defValue) {
        try {
            defValue = Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    public static int toInt(Object obj) {
        if (obj == null) {
            return 0;
        }
        return toInt(obj.toString(), 0);
    }

    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
            return false;
        }
    }

    public static String getString(String s) {
        return s == null ? "" : s;
    }



    public static String getSubString(int start, int num, String str) {
        if (str == null) {
            return "";
        }
        int leng = str.length();
        if (start < 0) {
            start = 0;
        }
        if (start > leng) {
            start = leng;
        }
        if (num < 0) {
            num = 1;
        }
        int end = start + num;
        if (end > leng) {
            end = leng;
        }
        return str.substring(start, end);
    }
}