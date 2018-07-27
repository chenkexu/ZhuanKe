package com.dfwr.zhuanke.zhuanke;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

import static com.dfwr.zhuanke.zhuanke.util.UIUtils.getResources;


/**
 * Created by lenovo on 2017/10/15.
 */

public class ShareMananger {

    //QQ
    public static void oneKeyShareLocalImage(final Context context, String imagePath,final String url) {
        final OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle(getResources().getString(R.string.app_name));

        oks.setText("我"+"邀您加入幸福康城");
        oks.setImagePath(imagePath);


        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                if ("Wechat".equals(platform.getName())) {
                    paramsToShare.setUrl(url);
                    paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                }
                if ("QQ".equals(platform.getName())) {
                    //点击跳转的url地址
                    paramsToShare.setTitleUrl(url);
                }
                if ("WechatMoments".equals(platform.getName())) {
                    paramsToShare.setUrl(url);
                    paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                }
            }
        });
        oks.show(context);
    }


    //微信和微信朋友圈分享
    public static void oneKeyShareWechat(Context context,final String imgurl) {
        final OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.addHiddenPlatform(QQ.NAME);
        oks.addHiddenPlatform(QZone.NAME);
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                if ("Wechat".equals(platform.getName())) {
                    paramsToShare.setImageUrl(imgurl);
                    paramsToShare.setShareType(Platform.SHARE_IMAGE);
                }
                if ("WechatMoments".equals(platform.getName())) {
                    paramsToShare.setImageUrl(imgurl);
                    paramsToShare.setShareType(Platform.SHARE_IMAGE);
                }
            }
        });

        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.d("ShareSDK", "onComplete ---->  分享成功");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.d("ShareSDK", "onError ---->  分享失败" + throwable.getStackTrace().toString());
                Log.d("ShareSDK", "onError ---->  分享失败" + throwable.getMessage());
                throwable.getMessage();
                throwable.printStackTrace();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.d("ShareSDK", "onCancel ---->  分享取消");
            }
        });
        oks.show(context);
    }











    //QQ分享
    public static void oneKeyShareQQImage(Context context, String imagePath) {
        final OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.addHiddenPlatform(Wechat.NAME);
        oks.addHiddenPlatform(WechatMoments.NAME);
        oks.setTitle("标题");
        oks.setText("我是共用的参数，这几个平台都有text参数要求，提取出来啦" + "http://sharesdk.cn");
        oks.setImageUrl("https://hmls.hfbank.com.cn/hfapp-api/9.png");
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                if ("Wechat".equals(paramsToShare.getVenueName())) {
                    paramsToShare.setUrl("http://design.blackgan.cn/xfkc1410.apk");
                    paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                }
                if ("QQ".equals(platform.getName())) {
                    //点击跳转的url地址
                    paramsToShare.setTitleUrl("http://design.blackgan.cn/xfkc1410.apk");
                }
                if ("WechatMoments".equals(platform.getName())) {
                    paramsToShare.setUrl("http://design.blackgan.cn/xfkc1410.apk");
                    paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                }
                if ("WechatMoments".equals(platform.getName())) {
                    paramsToShare.setUrl("http://design.blackgan.cn/xfkc1410.apk");
                    paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                }
            }
        });
        oks.show(context);
    }

}
