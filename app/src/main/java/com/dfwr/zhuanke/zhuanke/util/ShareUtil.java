package com.dfwr.zhuanke.zhuanke.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.wechatshare.AppConfig;
import com.google.zxing.WriterException;
import com.mob.tools.utils.BitmapHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class ShareUtil {
    public BitmapHelper bitmapHelper;
    public String code_bg_path = (FileUtil.getSDPath() + AppConfig.CODE_BG_PATH);
    public Context context;
    public String draw_bg_path = (FileUtil.getSDPath() + AppConfig.DRAW_BG_PATH);
    public String my_share_img_path = (FileUtil.getSDPath() + AppConfig.MY_SHARE_IMG_PATH);
    public String share_img_path = (FileUtil.getSDPath() + AppConfig.SHARE_IMG_PATH);


    public ShareUtil(Context context) {
        this.context = context;
    }



    public void shareToWeixinFriend(String code, int type) {
        Intent send = new Intent();
        send.setAction("android.intent.action.SEND");
        if (type == 1) {
            send.putExtra("android.intent.extra.STREAM", shareBitmap(code));
        } else {
            send.putExtra("android.intent.extra.STREAM", drawBitmap(code));
        }
        send.setType("image/*");
        send.setClassName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
        this.context.startActivity(send);
    }



    public void shareToCircleOfFriends(String str, String code, int type) {
        Intent send = new Intent();
        send.setAction("android.intent.action.SEND");
        send.putExtra("Kdescription", str);
        if (type == 1) {
            send.putExtra("android.intent.extra.STREAM", shareBitmap(code));
        } else {
            send.putExtra("android.intent.extra.STREAM", drawBitmap(code));
        }
        send.setType("image/*");
        send.setClassName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        this.context.startActivity(send);
    }



    public Uri shareBitmap(String url) {
        Uri imageUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(context, "com.dfwr.zhuanke.zhuanke.fileProvider",
                    new File(this.share_img_path));
        } else {
            imageUri = Uri.fromFile(new File(this.share_img_path));
        }
        try {
            Bitmap codeBitmap = QRcodeUtils.Create2DCode(url);
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.code_bg);
//            bitmap = drawBg4Bitmap(R.color.bg_white,bitmap);
//            Bitmap bitmap = BitmapFactory.decodeFile(this.code_bg_path);
            Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_4444);
            Canvas canvas = new Canvas(bmp);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
            canvas.drawBitmap(codeBitmap, (float) ((bitmap.getWidth() - codeBitmap.getWidth()) / 2), 850.0f, null);
            canvas.save(31);
            canvas.restore();
            saveBitmap(bmp, this.share_img_path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageUri;
    }



    public Bitmap getCodeBitmap(String url){
        Bitmap codeBitmap = null;
        try {
            codeBitmap = QRcodeUtils.Create2DCode(url);
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.code_bg);
//            bitmap = drawBg4Bitmap(R.color.bg_white,bitmap);
//            Bitmap bitmap = BitmapFactory.decodeFile(this.code_bg_path);
            Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_4444);
            Canvas canvas = new Canvas(bmp);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
            canvas.drawBitmap(codeBitmap, (float) ((bitmap.getWidth() - codeBitmap.getWidth()) / 2), 850.0f, null);
            canvas.save(31);
            canvas.restore();
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return codeBitmap;
    }


    public Uri drawBitmap(String url) {
        Uri imageUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(context, "com.dfwr.zhuanke.zhuanke.fileProvider",
                    new File(this.share_img_path));
        } else {
            imageUri = Uri.fromFile(new File(this.share_img_path));
        }
        try {
            Bitmap core = QRcodeUtils.Create2DCodeMin(url);
            Bitmap bitmap = BitmapFactory.decodeFile(this.draw_bg_path);
            Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
            Canvas cv = new Canvas(bmp);
            cv.drawBitmap(bitmap, 0.0f, 0.0f, null);
            cv.drawBitmap(core, 315.0f, 116.0f, null);
            cv.save(31);
            cv.restore();
            saveBitmap(bmp, this.my_share_img_path);
        }  catch (Exception e2) {
            e2.printStackTrace();
        }
        return imageUri;
    }




    private void saveBitmap(Bitmap bitmap, String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(CompressFormat.PNG, 90, out)) {
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}