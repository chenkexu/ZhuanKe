package com.dfwr.zhuanke.zhuanke.wechatshare;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.application.MyApplication;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.smtt.sdk.TbsConfig;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.URL;



public class ShareUtils {




    public static void throughIntentShareWXImage(Context context, String imagepath) {
        try {
            File file = new File(imagepath);
            Uri photoUri = null;
            if (file.exists()) {
                if (Build.VERSION.SDK_INT >= 24) {
                    try {
                        photoUri = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getPath(), "bigbang.jpg", null));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    photoUri = Uri.fromFile(file);
                }
            }
            Intent intentFriend = new Intent();
            intentFriend.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI"));
            intentFriend.setAction("android.intent.action.SEND");
            intentFriend.setType("image/*");
            intentFriend.putExtra("android.intent.extra.STREAM", photoUri);
            intentFriend.setFlags(268435457);
            context.startActivity(intentFriend);
        } catch (Exception e2) {
            if (e2.toString().contains("android.content.ActivityNotFoundException")) {
                Looper.prepare();
                ToastUtils.showShort("未发现微信");
                Looper.loop();
            }
            Logger.e(Log.getStackTraceString(e2));
        }
    }



    public static void throughIntentShareWXdesc(String share_word) {
        try {
            Intent intentFriend = new Intent();
            intentFriend.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI"));
            intentFriend.setAction("android.intent.action.SEND");
            intentFriend.setType("image/*");
            intentFriend.putExtra("android.intent.extra.TEXT", share_word);
            intentFriend.setFlags(268435457);
            MyApplication.getContext().startActivity(intentFriend);
        } catch (Exception e) {
        }
    }

    public static void throughIntentShareWXImage(String imageUri) {

        try {
            Intent intentFriend = new Intent();
            intentFriend.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI"));
            intentFriend.setAction("android.intent.action.SEND");
            intentFriend.setType("image/*");
            intentFriend.putExtra("android.intent.extra.STREAM", imageUri);
            intentFriend.setFlags(268435457);
            MyApplication.getContext().startActivity(intentFriend);
        } catch (Exception e) {
        }
    }

    public static void throughIntentShareWXCircle(String shareWord, Uri fileUri) {
        if (AppUtils.checkApkExists("com.tencent.mm")) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI"));
                intent.setAction("android.intent.action.SEND");
                intent.setType("image/*");
                intent.setFlags(268435457);
                intent.putExtra("Kdescription", shareWord);
                intent.putExtra("android.intent.extra.STREAM", fileUri);
                MyApplication.getContext().startActivity(intent);
                return;
            } catch (Exception e) {
                return;
            }
        }
//        ToastUtil.showToast(MyApplication.getContext(), "亲，你还没安装微信");
    }

    public static void throughIntentShareQQZONE(String desc, String fileUri) {
        if (fileUri != null) {
            try {
                Intent intentQZ = new Intent();
                intentQZ.setComponent(new ComponentName(TbsConfig.APP_QZONE, "com.qzonex.module.operation.ui.QZonePublishMoodActivity"));
                intentQZ.setAction("android.intent.action.SEND");
                intentQZ.setType("image/*");
                intentQZ.putExtra("android.intent.extra.TEXT", desc);
                intentQZ.putExtra("android.intent.extra.STREAM", fileUri);
                intentQZ.setFlags(268435457);
                MyApplication.getContext().startActivity(intentQZ);
            } catch (Exception e) {
            }
        }
    }

    public static void throughIntentShareQQDesc(String desc) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(TbsConfig.APP_QQ, "com.tencent.mobileqq.activity.JumpActivity"));
            intent.setAction("android.intent.action.SEND");
            intent.setType("text/*");
            intent.putExtra("android.intent.extra.TEXT", desc);
            intent.setFlags(268435457);
            MyApplication.getContext().startActivity(intent);
        } catch (Exception e) {
        }
    }

    public static void throughIntentShareQQImage(String fileUri) {
        if (fileUri != null) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(TbsConfig.APP_QQ, "com.tencent.mobileqq.activity.JumpActivity"));
                intent.setAction("android.intent.action.SEND");
                intent.setType("image/*");
                intent.putExtra("android.intent.extra.STREAM", fileUri);
                intent.setFlags(268435457);
                MyApplication.getContext().startActivity(intent);
            } catch (Exception e) {
            }
        }
    }

    public static void throughQQBShareWxCircle(String ucShareUrl) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(Uri.parse(ucShareUrl + "&type=weixinFriend"));
        intent.setClassName(Constant.WEIXINAPPPACKAGEQQBROWSER, "com.tencent.mtt.MainActivity");
        intent.setFlags(268435457);
        MyApplication.getContext().startActivity(intent);
    }

    public static void shareWX(WeakReference<Activity> weakReference, String appId, String packageName, String shareTitle, String shareContent, String shareUrl, int type, Bitmap shareBitmap, GetResultListener onShareLitener) {
        Bitmap localBitmap2 = Bitmap.createScaledBitmap(shareBitmap, 150, 150, true);
        if (shareBitmap != null) {
            shareBitmap.recycle();
        }
        WXWebpageObject localWXWebpageObject = new WXWebpageObject();
        localWXWebpageObject.webpageUrl = shareUrl;
        WXMediaMessage localWXMediaMessage = new WXMediaMessage(localWXWebpageObject);
        localWXMediaMessage.title = shareTitle;
        localWXMediaMessage.description = shareContent;
        localWXMediaMessage.thumbData = bmpToByteArray(localBitmap2, true);
        SendMessageToWX.Req localReq = new SendMessageToWX.Req();
        localReq.transaction = System.currentTimeMillis() + "";
        localReq.message = localWXMediaMessage;
        localReq.scene = type;
        WxShare.sendReq(weakReference, onShareLitener, localReq, appId, packageName);
    }

    public static void shareWXReady(WeakReference<Activity> weakReference, String shareTitle, String share_word, String shareUrl, int type, Bitmap bitmap, GetResultListener onShareLitener) {
        try {
            if (AppUtils.checkApkExists(Constant.WEIXINAPPPACKAGEQQ)) {
                shareWX(weakReference, Constant.WEIXINAPPKEYQQ, Constant.WEIXINAPPPACKAGEQQ, shareTitle, share_word, shareUrl, type, bitmap, onShareLitener);
            } else if (AppUtils.checkApkExists(Constant.WEIXINAPPPACKAGEUC)) {
                shareWX(weakReference, Constant.WEIXINAPPKEYUC, Constant.WEIXINAPPPACKAGEUC, shareTitle, share_word, shareUrl, type, bitmap, onShareLitener);
            } else if (AppUtils.checkApkExists(Constant.WEIXINAPPPACKAGEQQBROWSER)) {
                shareWX(weakReference, Constant.WEIXINAPPKEYQQBROWSER, Constant.WEIXINAPPPACKAGEQQBROWSER, shareTitle, share_word, shareUrl, type, bitmap, onShareLitener);
            } else if (AppUtils.checkApkExists(Constant.WEIXINAPPPACKAGENEWSTODAY)) {
                shareWX(weakReference, Constant.WEIXINAPPKEYNEWSTODAY, Constant.WEIXINAPPPACKAGENEWSTODAY, shareTitle, share_word, shareUrl, type, bitmap, onShareLitener);
            } else if (AppUtils.checkApkExists(Constant.WEIXINAPPPACKAGEBAIDU)) {
                shareWX(weakReference, Constant.WEIXINAPPKEYBAIDU, Constant.WEIXINAPPPACKAGEBAIDU, shareTitle, share_word, shareUrl, type, bitmap, onShareLitener);
            } else if (AppUtils.checkApkExists(Constant.WEIXINAPPPACKAGESINA)) {
                shareWX(weakReference, Constant.WEIXINAPPKEYSINA, Constant.WEIXINAPPPACKAGESINA, shareTitle, share_word, shareUrl, type, bitmap, onShareLitener);
            } else {
                onShareLitener.onError();
            }
        } catch (Exception e) {
            onShareLitener.onError();
        }
    }

    public static void shareWXRX(WeakReference<Activity> weakReference, String appId, String packageName, String shareTitle, String shareContent, String shareUrl, int type, Bitmap shareBitmap) {
        Bitmap localBitmap2 = Bitmap.createScaledBitmap(shareBitmap, 150, 150, true);
        if (shareBitmap != null) {
            shareBitmap.recycle();
        }
        WXWebpageObject localWXWebpageObject = new WXWebpageObject();
        localWXWebpageObject.webpageUrl = shareUrl;
        WXMediaMessage localWXMediaMessage = new WXMediaMessage(localWXWebpageObject);
        localWXMediaMessage.title = shareTitle;
        localWXMediaMessage.description = shareContent;
        localWXMediaMessage.thumbData = bmpToByteArray(localBitmap2, true);
        SendMessageToWX.Req localReq = new SendMessageToWX.Req();
        localReq.transaction = System.currentTimeMillis() + "";
        localReq.message = localWXMediaMessage;
        localReq.scene = type;
        WxShare.sendReq(weakReference, localReq, appId, packageName);
    }




    public static Bitmap getHttpBitmap(String shareImage) throws Throwable {
        URL url;
        Throwable th;
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            URL pictureUrl = new URL(shareImage);
            try {
                in = pictureUrl.openStream();
                Options options = new Options();
                options.inPreferredConfig = Config.RGB_565;
                options.inPurgeable = true;
                options.inSampleSize = 2;
                bitmap = (Bitmap) new SoftReference(BitmapFactory.decodeStream(in, null, options)).get();
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception e) {
                        url = pictureUrl;
                    }
                }
                url = pictureUrl;
            } catch (Exception e2) {
                url = pictureUrl;
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception e3) {
                    }
                }
                return bitmap;
            } catch (Throwable th2) {
                th = th2;
                url = pictureUrl;
                if (in != null) {
                    try {
                        in.close();
                    } catch (Exception e4) {
                    }
                }
                throw th;
            }
        } catch (Exception e5) {
            if (in != null) {
                in.close();
            }
            return bitmap;
        } catch (Throwable th3) {
            th = th3;
            if (in != null) {
                in.close();
            }
            throw th;
        }
        return bitmap;
    }

    public static void toInstallWebView(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(268435457);
        intent.setData(Uri.parse(url));
        MyApplication.getContext().startActivity(intent);
    }

    public static String buildTransaction(String type) {
        return type == null ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public static byte[] bmpToByteArray(Bitmap bmp, boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
