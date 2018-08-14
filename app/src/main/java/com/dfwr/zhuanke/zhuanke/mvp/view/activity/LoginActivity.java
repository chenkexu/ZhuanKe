package com.dfwr.zhuanke.zhuanke.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.MainActivity;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver;
import com.dfwr.zhuanke.zhuanke.api.param.ParamsUtil;
import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.UserBean;
import com.dfwr.zhuanke.zhuanke.bean.WechatBean;
import com.dfwr.zhuanke.zhuanke.util.GsonUtils;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesTool;
import com.meituan.android.walle.WalleChannelReader;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.iv_wechat)
    ImageView ivWechat;
    @BindView(R.id.iv_login)
    ImageView ivLogin;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.activity_guide)
    RelativeLayout activityGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        tvVersion.setText("版本"+AppUtils.getAppVersionName());
    }





    @Override
    protected BasePresenter createPresent() {
        return new BasePresenter() {
            @Override
            public void fecth() {

            }
        };
    }


    private void loginWeChat() {
        showDefaultLoading();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("AppId", "wxa75cd1b3294dbda4");
        hashMap.put("AppSecret", "02a13b899babead10f80ecf257a02202");
        hashMap.put("BypassApproval", "false");
        ShareSDK.setPlatformDevInfo(Wechat.NAME, hashMap);
        final Platform wechat = ShareSDK.getPlatform(Wechat.NAME);

        if (wechat.isClientValid()) {
            //客户端可用
        }

        if (wechat.isAuthValid()) {
            wechat.removeAccount(true);
        }

        wechat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, final HashMap<String, Object> hashMap) {
                /*platform.getDb().exportData()获取用户数据*/
                Log.d("ShareSDK", "onComplete ---->  登录成功" + platform.getDb().exportData());
                String json = platform.getDb().exportData();

                WechatBean wechatBean = GsonUtils.parseJsonToBean(json, WechatBean.class);


                HashMap<String, Object> map = ParamsUtil.getMap();
                map.put("wxId", wechatBean.getOpenid());
                map.put("wxName", platform.getDb().getUserName());
                map.put("sex", platform.getDb().getUserGender() == null ? "m" : platform.getDb().getUserGender());
                map.put("imgId", platform.getDb().getUserIcon());
                map.put("unionid", wechatBean.getUnionid());

                String channel = WalleChannelReader.getChannel(LoginActivity.this);
                Logger.d("channel = " + channel);
                if (channel == null) {
                    channel = "";
                    map.put("teacherName", channel);
                }else{
                    map.put("teacherName", "lexiangzhuan_"+channel);
                }

                ApiManager.getInstence().getApiService().login(ParamsUtil.getParams(map))
                        .compose(RxUtil.<ApiResponse<UserBean>>rxSchedulerHelper())
                        .subscribe(new BaseObserver<UserBean>() {
                            @Override
                            protected void onSuccees(ApiResponse<UserBean> t) {
                                hideDefaultLoading();
                                UserBean result = t.getResult();
                                SharedPreferencesTool.getInstance().setObject(result, SharedPreferencesTool.user);
                                // 这里授权成功跳转到程序主界面了
                                SharedPreferencesTool.getInstance().putBoolean(SharedPreferencesTool.USER_LOGOUT, false);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }

                            @Override
                            protected void onFailure(String errorInfo, boolean isNetWorkError) {
                                hideDefaultLoading();
                                ToastUtils.showShort(errorInfo);
                            }
                        });

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                ToastUtils.showShort("登录失败");
                hideDefaultLoading();
                Log.d("ShareSDK", "onError ---->  登录失败" + throwable.toString());
                Log.d("ShareSDK", "onError ---->  登录失败" + throwable.getStackTrace().toString());
                Log.d("ShareSDK", "onError ---->  登录失败" + throwable.getMessage());

            }

            @Override
            public void onCancel(Platform platform, int i) {
                hideDefaultLoading();
                ToastUtils.showShort("登录失败");
                Log.d("ShareSDK", "onCancel ---->  登录取消");
            }
        });
        wechat.SSOSetting(false);
        wechat.showUser(null);
    }


    @OnClick(R.id.iv_login)
    public void onViewClicked() {
        loginWeChat();
    }
}
