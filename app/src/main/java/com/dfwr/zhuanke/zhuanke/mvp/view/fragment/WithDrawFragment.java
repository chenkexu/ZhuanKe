package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BaseTwoFragment;
import com.dfwr.zhuanke.zhuanke.bean.UserBaseInfo;
import com.dfwr.zhuanke.zhuanke.bean.UserBean;
import com.dfwr.zhuanke.zhuanke.mvp.contract.IHomeView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.HomePresent;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.BindAlipayActivity;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.GoWithDrawActivity;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.PhoneWithDrawActivity;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesTool;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ckx on 2018/7/12.
 * 提现
 */

public class WithDrawFragment extends BaseTwoFragment<IHomeView, HomePresent<IHomeView>> implements IHomeView {


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
    protected HomePresent<IHomeView> createPresent() {
        return new HomePresent<>(this);
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
                startActivity(new Intent(getActivity(), GoWithDrawActivity.class));
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
        tvBalance.setText(userBaseInfo.getUser().getBalance()+"");
        tvAllWithDraw.setText(userBaseInfo.getUser().getBalance()+"");
        tvAllWithDraw.setText(userBaseInfo.getAllWithDrawMoney()+"");
    }
}
