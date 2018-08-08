package com.dfwr.zhuanke.zhuanke.mvp.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.widget.ProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by wy on 2017/10/31.
 * webView页面
 */
public class MyWebView extends BaseActivity {

    @BindView(R.id.my_title)
    ImageView myTitle;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.layout_title)
    RelativeLayout layoutTitle;
    @BindView(R.id.webView)
    ProgressWebView webView;

    private String url = "";
    private String title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_webview);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");
        textTitle.setText("赚钱攻略");
        initView();
    }


    private void initView() {
        webView.loadUrl(url);
    }



    @OnClick(R.id.my_title)
    public void onViewClicked() {
        finish();
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
