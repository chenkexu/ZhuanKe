package com.dfwr.zhuanke.zhuanke;

import android.os.Bundle;

import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.mvp.contract.IMsgView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.MsgPresent;

public class MainActivity extends BaseActivity<IMsgView,MsgPresent<IMsgView>> implements IMsgView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected MsgPresent<IMsgView> createPresent() {
        return new MsgPresent<>(this);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
