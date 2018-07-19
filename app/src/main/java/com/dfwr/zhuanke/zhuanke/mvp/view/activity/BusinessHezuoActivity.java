package com.dfwr.zhuanke.zhuanke.mvp.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.util.UIUtils;
import com.dfwr.zhuanke.zhuanke.widget.MyTitle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ckx on 2018/7/17.
 */

public class BusinessHezuoActivity extends BaseActivity {


    @BindView(R.id.my_title)
    MyTitle myTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_hezuo);
        ButterKnife.bind(this);
        myTitle.setTitleName("商务合作");
        myTitle.setImageBack(this);

    }

    @Override
    protected BasePresenter createPresent() {
        return new BasePresenter() {
            @Override
            public void fecth() {

            }
        };
    }


    @OnClick(R.id.rl_add_qq)
    public void onViewClicked() {
        final String url = "mqqwpa://im/chat?chat_type=wpa&uin=2531111991";
        if (UIUtils.checkApkExist(this, "com.tencent.mobileqq")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } else {
            ToastUtils.showShort("本机未安装QQ应用,请下载安装。");
        }

    }
}
