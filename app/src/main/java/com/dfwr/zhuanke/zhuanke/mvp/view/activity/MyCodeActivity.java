package com.dfwr.zhuanke.zhuanke.mvp.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.Propertie;
import com.dfwr.zhuanke.zhuanke.bean.UserBean;
import com.dfwr.zhuanke.zhuanke.util.GlideUtil;
import com.dfwr.zhuanke.zhuanke.util.QRcodeUtils;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesUtil;
import com.dfwr.zhuanke.zhuanke.util.UserDataManeger;
import com.dfwr.zhuanke.zhuanke.widget.Systems;
import com.google.zxing.WriterException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ckx on 2018/7/17.
 */

public class MyCodeActivity extends BaseActivity {

    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.activity_guide)
    LinearLayout activityGuide;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.tv_tips2)
    TextView tvTips2;
    @BindView(R.id.iv_back)
    ImageView ivBack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_code);
        ButterKnife.bind(this);
        UserBean userBean = UserDataManeger.getInstance().getUserBean();
        if (userBean != null) {
            tvName.setText(userBean.getUser().getWxName());
            GlideUtil.getInstance().loadHeadImage(this, ivHead, userBean.getUser().getImgId(), true);
        }
        String studentLink = SharedPreferencesUtil.getStringData(this, SharedPreferencesUtil.student_link);

        try {
            Bitmap bitmap = QRcodeUtils.Create2DCode(studentLink);
            ivCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }


        Intent intent = getIntent();
        Propertie propertie = (Propertie) intent.getSerializableExtra(Systems.propertie);

        if (propertie!=null) {
            String price1 = propertie.getRegister_reward();
            String price2 = propertie.getGet_balance_money_min();



            String string = getResources().getString(R.string.my_code_tips, price1, price2);
            tvTips.setText(Html.fromHtml(string));


            String percentStr = propertie.getStudent_reward();
            String percentStr2 = propertie.getGrandson_reward();
            String string2 = getResources().getString(R.string.my_code_tips2, percentStr, percentStr2);
            tvTips2.setText(Html.fromHtml(string2));
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


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
