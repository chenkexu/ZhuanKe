package com.dfwr.zhuanke.zhuanke.wechatshare;

import android.content.pm.PackageInfo;

import com.dfwr.zhuanke.zhuanke.application.MyApplication;

import java.security.MessageDigest;
import java.util.List;

import javax.security.auth.x500.X500Principal;

public final class AppUtils {
    private static final boolean DEBUG = true;
    private static final X500Principal DEBUG_DN = new X500Principal("CN=Android Debug,O=Android,C=US");
    private static final String TAG = "AppUtils";

    private AppUtils() {
        throw new Error("Do not need instantiate!");
    }

//
//    public static boolean checkApkExist(String packageName) {
//        if (StringUtils.isEmpty(packageName)) {
//            return false;
//        }
//        List<PackageInfo> pinfo = Application.getContext().getPackageManager().getInstalledPackages(0);
//        if (pinfo != null) {
//            for (int i = 0; i < pinfo.size(); i++) {
//                if (((PackageInfo) pinfo.get(i)).packageName.equals(packageName)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }




    private static String hexdigest(byte[] paramArrayOfByte) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            char[] arrayOfChar = new char[32];
            int i = 0;
            int j = 0;
            while (i < 16) {
                int k = arrayOfByte[i];
                arrayOfChar[j] = hexDigits[(k >>> 4) & 15];
                j++;
                arrayOfChar[j] = hexDigits[k & 15];
                i++;
                j++;
            }
            return new String(arrayOfChar);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }




    public static boolean checkApkExists(String packageName) {
        if (StringUtils.isEmpty(packageName)) {
            return false;
        }
        List<PackageInfo> pinfo = MyApplication.getContext().getPackageManager().getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                if (((PackageInfo) pinfo.get(i)).packageName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }



}