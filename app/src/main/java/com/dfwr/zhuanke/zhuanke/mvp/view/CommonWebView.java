package com.dfwr.zhuanke.zhuanke.mvp.view;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.api.HttpContants;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.Article;
import com.dfwr.zhuanke.zhuanke.bean.UserBean;
import com.dfwr.zhuanke.zhuanke.util.UIUtils;
import com.dfwr.zhuanke.zhuanke.util.UserDataManeger;
import com.dfwr.zhuanke.zhuanke.wechatshare.GetResultListener;
import com.dfwr.zhuanke.zhuanke.wechatshare.ShareUtils;
import com.dfwr.zhuanke.zhuanke.widget.MyTitle;
import com.dfwr.zhuanke.zhuanke.widget.Systems;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import java.lang.ref.WeakReference;

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
    private String share_host;
    private String url = "";
    private Article article;
    private Bitmap httpBitmap;
    private String articleLink;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_webview);
        ButterKnife.bind(this);
        myTitle.setImageBack(this);
        Intent intent = getIntent();
        myTitle.setTitleName("文章阅读");
        share_host = intent.getStringExtra(Systems.share_host);
        article = (Article) intent.getSerializableExtra(Systems.articleData);
        initView();
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
        webView.loadUrl(HttpContants.article_details + article.getAid());



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
            }
        });

        final String headImg = article.getHeadImg();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    httpBitmap = UIUtils.getHttpBitmap(headImg);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }).start();

        articleLink = share_host + HttpContants.share;
        UserBean userBean = UserDataManeger.getInstance().getUserBean();
        if (userBean!=null) {
            articleLink = articleLink + "UID=" + userBean.getUser().getUid() + "&AID=" + article.getAid();
        }

    }


    @OnClick({R.id.ivWechat, R.id.ivWechatFriend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivWechat:
                share(article.getTitle(),article.getTitle(),SendMessageToWX.Req.WXSceneSession,httpBitmap,articleLink);
                break;
            case R.id.ivWechatFriend:
                share(article.getTitle(),article.getTitle(),SendMessageToWX.Req.WXSceneTimeline,httpBitmap,articleLink);
                break;
        }
    }






    //分享
    private void share(String title,String content,int type,Bitmap bitmap,String clickUrl) {
        Logger.d("clickUrl:"+clickUrl);
        int wxSceneSession = SendMessageToWX.Req.WXSceneSession; //聊天界面
        int wxSceneTimeline = SendMessageToWX.Req.WXSceneTimeline;//朋友圈
        ShareUtils.shareWXReady(new WeakReference(this), title, content, clickUrl, type, bitmap, new GetResultListener() {
            @Override
            public void onError() {

            }

            @Override
            public void onSuccess(Object object) {

            }
        });
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



    @Override
    protected BasePresenter createPresent() {
        return new BasePresenter() {
            @Override
            public void fecth() {

            }
        };
    }

}
