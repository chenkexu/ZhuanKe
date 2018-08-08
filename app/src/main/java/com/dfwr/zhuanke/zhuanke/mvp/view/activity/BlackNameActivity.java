package com.dfwr.zhuanke.zhuanke.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;

/**
 * Created by ckx on 2018/7/17.
 */

public class BlackNameActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_error);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected BasePresenter createPresent() {
        return new BasePresenter() {
            @Override
            public void fecth() {

            }
        };
    }
}
