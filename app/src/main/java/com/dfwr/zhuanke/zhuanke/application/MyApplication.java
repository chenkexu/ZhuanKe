package com.dfwr.zhuanke.zhuanke.application;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesTool;
import com.mob.MobSDK;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * Created by ckx on 2018/7/12.
 */

public class MyApplication extends Application {

    private static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this,"1ea90b79ee1c3","3ad9421238237570c51625a436d4b85e");
        applicationContext = MyApplication.this.getApplicationContext();
        //工具类库
        Utils.init(this);
        SharedPreferencesTool.getInstance().init(this);

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("okgo")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
        });
    }


    public static Context getContext() {
        return applicationContext;
    }

}
