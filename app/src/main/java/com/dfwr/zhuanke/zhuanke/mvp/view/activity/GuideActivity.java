package com.dfwr.zhuanke.zhuanke.mvp.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.MainActivity;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BasePermissionActivity;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesTool;



/**
 * 欢迎界面、快闪页面
 */
public class GuideActivity extends BasePermissionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            //取消标题栏
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_splash);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    requestPermission(new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE

                    });

                }
            }, 1000);

        }

    @Override
    protected void permissionSuccess() {
        super.permissionSuccess();
        boolean loginout = SharedPreferencesTool.getInstance().getBooleanValue(SharedPreferencesTool.USER_LOGOUT, true);
        if (loginout) {   //已经退出,进入登录界面，或者找不到该变量（默认为true）
            startActivity(new Intent(GuideActivity.this, LoginActivity.class));
            finish();
        } else { //false进入主页面
            startActivity(new Intent(GuideActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void permissionFail() {
        super.permissionFail();
        ToastUtils.showShort("授权失败");
        finish();
    }
}
