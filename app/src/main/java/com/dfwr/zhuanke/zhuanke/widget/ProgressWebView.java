package com.dfwr.zhuanke.zhuanke.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.dfwr.zhuanke.zhuanke.R;


public class ProgressWebView extends WebView {

    private ProgressBar progressbar;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                10, 0, 0));

        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);

        setWebChromeClient(new WebChromeClient());
        getSettings().setDefaultTextEncodingName("utf-8");
        getSettings().setJavaScriptEnabled(true);
        getSettings().setAllowFileAccess(true);
        getSettings().setAllowFileAccessFromFileURLs(true);
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        getSettings().setLoadsImagesAutomatically(true);
        getSettings().setGeolocationEnabled(true);
        getSettings().setDomStorageEnabled(true);
        //设置自适应屏幕，两者合用
        getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        getSettings().setUseWideViewPort(true); //将图片调整到适合webview的大小
        getSettings().setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        // 设置与Js交互的权限
        // 设置允许JS弹窗
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setSupportZoom(true);
       // SMALLEST(50%),  SMALLER(75%),          NORMAL(100%),          LARGER(150%),          LARGEST(200%);
        getSettings().setTextSize(WebSettings.TextSize.SMALLER);

    }


    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

    }



    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}   