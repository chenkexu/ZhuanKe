package com.dfwr.zhuanke.zhuanke.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.bean.UserBean;
import com.dfwr.zhuanke.zhuanke.mvp.contract.BindPhoneView;
import com.dfwr.zhuanke.zhuanke.mvp.event.UpdateSmsStateEvent;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.BindPhonePresent;
import com.dfwr.zhuanke.zhuanke.util.UserDataManeger;
import com.dfwr.zhuanke.zhuanke.widget.MyTitle;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ckx on 2018/7/17.
 */

public class BindPhoneActivity extends BaseActivity<BindPhoneView, BindPhonePresent<BindPhoneView>> implements BindPhoneView {


    @BindView(R.id.my_title)
    MyTitle myTitle;

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.tv_call_code)
    TextView tvCallCode;
    @BindView(R.id.et_auth_code)
    EditText etAuthCode;
    @BindView(R.id.rl_auth_code)
    RelativeLayout rlAuthCode;
    @BindView(R.id.btn_login)
    Button btnBind;
    @BindView(R.id.tv_name)
    TextView tvName;
    String phoneNo="";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    private void initView() {
        myTitle.setImageBack(this);
        myTitle.setTitleName("绑定手机号");
    }

    private void initData() {
        UserBean userBean = UserDataManeger.getInstance().getUserBean();
        if (userBean!=null) {
            tvName.setText("昵称："+userBean.getUser().getWxName());
        }
    }


    @OnClick({R.id.btn_login, R.id.tv_call_code})
    public void onViewClicked(View view) {
        phoneNo = etAccount.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btn_login:
                if (!RegexUtils.isMobileExact(phoneNo)) {
                    ToastUtils.showShort("请输入正确的手机号");
                    return;
                }
                String code = etAuthCode.getText().toString();
                if(TextUtils.isEmpty(code)){
                    ToastUtils.showShort("请输入验证码！");
                    return;
                }
                mPresent.BindPhone(phoneNo,code);

                break;
            case R.id.tv_call_code:
                if (TextUtils.isEmpty(phoneNo)) {
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                if (!RegexUtils.isMobileExact(phoneNo)) {
                    ToastUtils.showShort("请输入正确的手机号");
                    return;
                }
                mPresent.sendMessageCode(phoneNo);
                break;
        }
    }



    @Subscribe
    public void updateSmsState(UpdateSmsStateEvent updateSmsTimeEvent) {
        if (updateSmsTimeEvent.isCall) {
            tvCallCode.setText("再次获取");
            tvCallCode.setClickable(true);
            tvCallCode.setBackground(getResources().getDrawable(R.drawable.bg_code_round));
        } else {
            tvCallCode.setText(updateSmsTimeEvent.surplusTime);
            tvCallCode.setBackground(getResources().getDrawable(R.drawable.round_border_gray));
            tvCallCode.setClickable(false);
        }
    }


    @Override
    protected BindPhonePresent<BindPhoneView> createPresent() {
        return new BindPhonePresent<>(this);
    }


    @Override
    public void showLoading() {
        showDefaultLoading();
    }

    @Override
    public void hideLoading() {
        hideDefaultLoading();
    }


    @Override
    public void bindPhoneSuccess(Object object) {
        Intent intent = new Intent(this,GoWithDrawActivity.class);
        startActivity(intent);
    }
}
