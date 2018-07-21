package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BaseTwoFragment;
import com.dfwr.zhuanke.zhuanke.bean.CheckWithDrawBean;
import com.dfwr.zhuanke.zhuanke.bean.UserBaseInfo;
import com.dfwr.zhuanke.zhuanke.bean.UserBean;
import com.dfwr.zhuanke.zhuanke.mvp.contract.HomeWithDrawView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.HomeWithDrawPresent;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.AttentionWechatNumberActivity;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.BindAlipayActivity;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.BindPhoneActivity;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.GoWithDrawActivity;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.PhoneWithDrawActivity;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesTool;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesUtil;
import com.dfwr.zhuanke.zhuanke.widget.Systems;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ckx on 2018/7/12.
 * 提现
 */

public class WithDrawFragment extends BaseTwoFragment<HomeWithDrawView, HomeWithDrawPresent<HomeWithDrawView>> implements HomeWithDrawView {


    @BindView(R.id.tv_account)
    TextView tvBalance;
    @BindView(R.id.tv_all_withdraw)
    TextView tvAllWithDraw;



    @BindView(R.id.ll_withdraw_wechat)
    LinearLayout llWithdrawWechat;
    @BindView(R.id.ll_withdraw_alipay)
    LinearLayout llWithdrawAlipay;
    @BindView(R.id.ll_withdraw_phone)
    LinearLayout llWithdrawPhone;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_winthdraw;
    }

    @Override
    protected void initData() {
        super.initData();
        setData();

    }


    private void setData() {
        UserBean userBean = (UserBean) SharedPreferencesTool.getObjectFromShare(SharedPreferencesTool.user);
        mPresent.getUserInfo();
    }


    @Override
    protected HomeWithDrawPresent<HomeWithDrawView> createPresent() {
        return new HomeWithDrawPresent<>(this);
    }


    @Override
    public void showLoading() {
        showDefaultLoading();
    }

    @Override
    public void hideLoading() {
        hideDefaultLoading();
    }


    @OnClick({R.id.ll_withdraw_wechat, R.id.ll_withdraw_alipay, R.id.ll_withdraw_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_withdraw_wechat:
//                mPresent.checkWithDraw();
                Intent intent = new Intent(getActivity(), GoWithDrawActivity.class);
                intent.putExtra(Systems.isFirstWithDraw, false);
                startActivity(intent);
//                startActivity(new Intent(getActivity(), BindPhoneActivity.class));
                break;
            case R.id.ll_withdraw_alipay:
                startActivity(new Intent(getActivity(), BindAlipayActivity.class));
                break;
            case R.id.ll_withdraw_phone:
                startActivity(new Intent(getActivity(), PhoneWithDrawActivity.class));
                break;
        }
    }







    @Override
    protected void fragmentToUserVisible() {
        super.fragmentToUserVisible();
        setData();
    }

    @Override
    public void getUserInfo(UserBaseInfo userBaseInfo) {
        tvBalance.setText(userBaseInfo.getAccount().getBalance()+"");
        tvAllWithDraw.setText(userBaseInfo.getUser().getBalance()+"");
        tvAllWithDraw.setText(userBaseInfo.getAllWithDrawMoney()+"");
        SharedPreferencesUtil.putStringData(getActivity(), SharedPreferencesTool.balance,userBaseInfo.getAccount().getBalance()+"");
    }

    @Override
    public void getCheckWithDrawSuccess(CheckWithDrawBean checkWithDrawBean) {
        if (checkWithDrawBean.getPhoneIsBinding() == 0) { //没绑定手机号
            startActivity(new Intent(getActivity(), BindPhoneActivity.class));
        }else if(checkWithDrawBean.getGongzhonghao() == 0){ //没关注微信
            startActivity(new Intent(getActivity(), AttentionWechatNumberActivity.class));
        }else {
            Intent intent = new Intent(getActivity(), GoWithDrawActivity.class);
            if (checkWithDrawBean.getNum() == 0) { //是否首次提现 0，没有提现记录
                intent.putExtra(Systems.isFirstWithDraw, true);
            }else{
                intent.putExtra(Systems.isFirstWithDraw, false);
            }
            startActivity(intent);
        }
    }
}
