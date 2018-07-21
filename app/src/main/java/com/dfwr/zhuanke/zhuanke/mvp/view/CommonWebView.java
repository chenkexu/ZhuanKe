package com.dfwr.zhuanke.zhuanke.mvp.view;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.FeedArticleData;
import com.dfwr.zhuanke.zhuanke.widget.MyTitle;
import com.dfwr.zhuanke.zhuanke.widget.Systems;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by wy on 2017/10/31.
 * webView页面
 */
public class CommonWebView extends BaseActivity {

    @BindView(R.id.my_title)
    MyTitle myTitle;

    private WebView webView;
    private String title = "11";
    private String url = "";
    private FeedArticleData feedArticleData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_webview);
        ButterKnife.bind(this);
        myTitle.setImageBack(this);
        Intent intent = getIntent();
        title = intent.getStringExtra(Systems.title);

        myTitle.setTitleName(title);
        initView();
    }


    @Override
    protected BasePresenter createPresent() {
        return new BasePresenter() {
            @Override
            public void fecth() {

            }
        };
    }


    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setGeolocationEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        //设置自适应屏幕，两者合用
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.getSettings().setUseWideViewPort(true); //将图片调整到适合webview的大小
        webView.getSettings().setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webView.setWebChromeClient(new WebChromeClient());
        // 设置与Js交互的权限
        // 设置允许JS弹窗
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(false);


        webView.loadUrl("file:///android_asset/www/bb.html");






        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                webView.loadUrl("javascript:pushNewsDate('"+title+"')");
            }
        });


    }

    @OnClick(R.id.btn_share)
    public void onViewClicked() {

//        ShareMananger.oneKeyShareWechat(this,feedArticleData.getTitle(),feedArticleData.getDesc(),feedArticleData.getEnvelopePic());
    }


    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                hideDefaultLoading();
            } else {
                showDefaultLoading();
            }
            super.onProgressChanged(view, newProgress);
        }

    }
}
