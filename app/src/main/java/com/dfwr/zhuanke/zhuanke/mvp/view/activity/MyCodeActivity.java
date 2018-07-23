package com.dfwr.zhuanke.zhuanke.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.UserBean;
import com.dfwr.zhuanke.zhuanke.util.GlideUtil;
import com.dfwr.zhuanke.zhuanke.util.UserDataManeger;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_code);
        ButterKnife.bind(this);
        UserBean userBean = UserDataManeger.getInstance().getUserBean();
        if (userBean!=null) {
            tvName.setText(userBean.getUser().getWxName());
            GlideUtil.getInstance().loadHeadImage(this,ivHead,userBean.getUser().getImgId(),true);
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
