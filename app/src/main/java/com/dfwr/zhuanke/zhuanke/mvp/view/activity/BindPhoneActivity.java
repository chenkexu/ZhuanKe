package com.dfwr.zhuanke.zhuanke.mvp.view.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.UserBean;
import com.dfwr.zhuanke.zhuanke.mvp.event.UpdateSmsStateEvent;
import com.dfwr.zhuanke.zhuanke.util.UserDataManeger;
import com.dfwr.zhuanke.zhuanke.widget.MyTitle;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ckx on 2018/7/17.
 */

public class BindPhoneActivity extends BaseActivity {


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
        switch (view.getId()) {
            case R.id.btn_login:

                break;
            case R.id.tv_call_code:
                final String phoneNo = etAccount.getText().toString().trim();
                if (TextUtils.isEmpty(phoneNo)) {
                    ToastUtils.showShort("请输入手机号");
                    return;
                }
                if (!RegexUtils.isTel(phoneNo)) {
                    ToastUtils.showShort("请输入正确的手机号");
                    return;
                }
                showDefaultLoading();

                break;
        }
    }


    //验证码Id
    private String codeId = "";
    private long totalTime = 60000;
    private long progress = 0;
    private CountDownTimer timer;

    //倒计时开始
    private void countDown() {
        progress = totalTime;
        timer = new CountDownTimer(totalTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //不可再次获取验证码
                progress -= 1000;
                String currentTime = progress / 1000 + "s";
                UpdateSmsStateEvent updateSmsStateEvent = new UpdateSmsStateEvent();
                updateSmsStateEvent.isCall = false;
                updateSmsStateEvent.surplusTime = currentTime;
                EventBus.getDefault().post(updateSmsStateEvent);
            }

            @Override
            public void onFinish() {
                //可以再次获取验证码
                progress = totalTime;
                UpdateSmsStateEvent updateSmsStateEvent = new UpdateSmsStateEvent();
                updateSmsStateEvent.isCall = true;
                updateSmsStateEvent.surplusTime = "0s";
                EventBus.getDefault().post(updateSmsStateEvent);
            }
        };
        timer.start();
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
