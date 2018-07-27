package com.dfwr.zhuanke.zhuanke.application;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.dfwr.zhuanke.zhuanke.bean.UserBean;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesTool;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesUtil;
import com.dfwr.zhuanke.zhuanke.widget.Systems;
import com.meituan.android.walle.WalleChannelReader;
import com.mob.MobSDK;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/**
 * Created by ckx on 2018/7/12.
 */

public class MyApplication extends Application {

    private static Context applicationContext;
    private UserBean loginBean;
    public static IWXAPI mWxApi;

    @Override
    public void onCreate() {
        super.onCreate();
        String channel = WalleChannelReader.getChannel(this.getApplicationContext());
        Logger.d("channel = " + channel);

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

//        registerToWX();
    }

//
//    private void registerToWX() {
//        //第二个参数是指你应用在微信开放平台上的AppID
//        mWxApi = WXAPIFactory.createWXAPI(this, MainConstant.WX.WEIXIN_APP_ID, false);
//        // 将该app注册到微信
//        mWxApi.registerApp(MainConstant.WX.WEIXIN_APP_ID);
//    }
//}
    public static Context getContext() {
        return applicationContext;
    }


    /**
     * 注销用户
     */
    public void logOutLogin(Context context) {
        loginBean = null;
        SharedPreferencesUtil.removeData(context, Systems.USER_INFO);
    }

    /**
     * 保存用户信息
     *
     * @param loginBean
     */
    public void saveLoginBean(UserBean loginBean) {
        this.loginBean = loginBean;
        SharedPreferencesUtil.save(Systems.USER_INFO, loginBean);
    }

    /**
     * 判断用户是否登录
     *
     * @return
     */
    public boolean isMemberLogin() {
        if (getLoginBean() != null) {
            return true;
        }
        return false;
    }

    /**
     * 获取用户详细信息
     *
     * @return
     */
    public UserBean getLoginBean() {
        if (loginBean != null) {
            return loginBean;
        }
        loginBean = (UserBean) SharedPreferencesUtil.get(Systems.USER_INFO);
        return loginBean;
    }



}
