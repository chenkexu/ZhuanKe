package com.dfwr.zhuanke.zhuanke.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.MainActivity;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver;
import com.dfwr.zhuanke.zhuanke.api.param.ParamsUtil;
import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.bean.UserBean;
import com.dfwr.zhuanke.zhuanke.bean.WechatBean;
import com.dfwr.zhuanke.zhuanke.util.GsonUtils;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesTool;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    private void loginWeChat() {
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
                map.put("sex", platform.getDb().getUserGender());
                map.put("imgId", platform.getDb().getUserIcon());
                map.put("unionid", wechatBean.getUnionid());

                ApiManager.getInstence().getApiService().login(ParamsUtil.getParams(map))
                        .compose(RxUtil.<ApiResponse<UserBean>>rxSchedulerHelper())
                        .subscribe(new BaseObserver<UserBean>() {
                            @Override
                            protected void onSuccees(ApiResponse<UserBean> t) {
                                UserBean result = t.getResult();
                                SharedPreferencesTool.getInstance().setObject(result, SharedPreferencesTool.user);
                                // 这里授权成功跳转到程序主界面了
                                SharedPreferencesTool.getInstance().putBoolean(SharedPreferencesTool.USER_LOGOUT,false);
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }

                            @Override
                            protected void onFailure(String errorInfo, boolean isNetWorkError) {
                                ToastUtils.showShort(errorInfo);
                            }
                        });





            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.d("ShareSDK", "onError ---->  登录失败" + throwable.toString());
                Log.d("ShareSDK", "onError ---->  登录失败" + throwable.getStackTrace().toString());
                Log.d("ShareSDK", "onError ---->  登录失败" + throwable.getMessage());

            }
            @Override
            public void onCancel(Platform platform, int i) {
                Log.d("ShareSDK", "onCancel ---->  登录取消");
            }
        });
        wechat.SSOSetting(false);
        wechat.showUser(null);
    }

    @OnClick(R.id.iv_logo)
    public void onViewClicked() {
        loginWeChat();
    }
}
