package com.dfwr.zhuanke.zhuanke.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by ckx on 2018/8/20.
 */

public class ConvertCodeUtil {



    /**
     * 把中文字符串转换为十六进制Unicode编码字符串
     */
/*    public static String stringToUnicode(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            if (ch > 255)
                str += "\\u" + Integer.toHexString(ch);
            else
                str += String.valueOf(s.charAt(i));
        }
        return str;
    }*/

    /**
     * 把中文字符串转换为十六进制Unicode编码字符串
     */
    public static String stringToUnicode(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            if (ch > 255)
                str += "\\u" + Integer.toHexString(ch);
            else
                str += String.valueOf(s.charAt(i));
        }
        return str;
    }


    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
                int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }


    /**
     * 字符串换成UTF-8
     *
     * @param str
     * @return
     */
    public static String stringToUtf8(String str) {
        String result = null;
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


    /**
     * utf-8换成字符串
     *
     * @param str
     * @return
     */
    public static String utf8ToString(String str) {
        String result = null;
        try {
            result = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
