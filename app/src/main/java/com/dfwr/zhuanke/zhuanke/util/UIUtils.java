package com.dfwr.zhuanke.zhuanke.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.view.View;

import com.dfwr.zhuanke.zhuanke.application.MyApplication;


/**
 *
 * 界面相关的工具类
 *
 * Created by xzhang on 2017/5/15.
 */

public class UIUtils {

    /** dip转换px */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /** 获取上下文 */
    public static Context getContext() {
        return MyApplication.getContext();
    }

    /** pxz转换dip */

    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    /** 获取字符数组 */
    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }

    /** 获取颜色id */
    public static int getColor(int colorId) {
        return getResources().getColor(colorId);
    }

    /** 根据id获取尺寸 */
    public static int getDimens(int id) {
        return getResources().getDimensionPixelSize(id);
    }

    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }




    public static boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    public static Uri setQQUri(String key) {
        Uri data = Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key);
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        return data;
    }

}
