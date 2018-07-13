package com.dfwr.zhuanke.zhuanke.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.dfwr.zhuanke.zhuanke.MainActivity;
import com.dfwr.zhuanke.zhuanke.R;
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
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }


    private void loginWeChat() {
        Toast.makeText(this, "Wechat登录开始", Toast.LENGTH_SHORT).show();

        final Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                /*final Platform qq = ShareSDK.getPlatform(QQ.NAME);*/
                /*final Platform sinaweibo = ShareSDK.getPlatform(SinaWeibo.NAME);*/
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
                platform.getDb().getUserId();
                // 这里授权成功跳转到程序主界面了
                SharedPreferencesTool.getInstance().putBoolean(SharedPreferencesTool.USER_LOGOUT,false);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
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
