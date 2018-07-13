package com.dfwr.zhuanke.zhuanke;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.mvp.contract.IMsgView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.MsgPresent;
import com.dfwr.zhuanke.zhuanke.mvp.view.fragment.MasterFragment;
import com.dfwr.zhuanke.zhuanke.mvp.view.fragment.MeFragment;
import com.dfwr.zhuanke.zhuanke.mvp.view.fragment.NewsFragment;
import com.dfwr.zhuanke.zhuanke.mvp.view.fragment.WithDrawFragment;
import com.dfwr.zhuanke.zhuanke.util.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IMsgView, MsgPresent<IMsgView>> implements IMsgView {


    @BindView(R.id.content)
    FrameLayout content;

    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ll_category)
    LinearLayout llCategory;
    @BindView(R.id.ll_service)
    LinearLayout llService;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;


    private NewsFragment newsFragment;
    private MasterFragment masterFragment;
    private WithDrawFragment withDrawFragment;
    private MeFragment meFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        selectedFragment(0);
        tabSelected(llHome);
    }


    @Override
    protected MsgPresent<IMsgView> createPresent() {
        return new MsgPresent<>(this);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick({R.id.ll_home, R.id.ll_category, R.id.ll_service, R.id.ll_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                selectedFragment(0);
                tabSelected(llHome);
                break;
            case R.id.ll_category:
                selectedFragment(1);
                tabSelected(llCategory);
                break;
            case R.id.ll_service:
                selectedFragment(2);
                tabSelected(llService);
                break;
            case R.id.ll_mine:
                selectedFragment(3);
                tabSelected(llMine);
                break;
        }
    }


    private void selectedFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    transaction.add(R.id.content, newsFragment);
                } else
                    transaction.show(newsFragment);
                break;
            case 1:
                if (masterFragment == null) {
                    masterFragment = new MasterFragment();
                    transaction.add(R.id.content, masterFragment);
                } else
                    transaction.show(masterFragment);
                break;
            case 2:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    transaction.add(R.id.content, meFragment);
                } else
                    transaction.show(meFragment);
                break;
            case 3:
                if (withDrawFragment == null) {
                    withDrawFragment = new WithDrawFragment();
                    transaction.add(R.id.content, withDrawFragment);
                } else
                    transaction.show(withDrawFragment);
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (newsFragment != null)
            transaction.hide(newsFragment);
        if (masterFragment != null)
            transaction.hide(masterFragment);
        if (withDrawFragment != null)
            transaction.hide(withDrawFragment);
        if (meFragment != null)
            transaction.hide(meFragment);
    }

    private void tabSelected(LinearLayout linearLayout) {
        llHome.setSelected(false);
        llCategory.setSelected(false);
        llService.setSelected(false);
        llMine.setSelected(false);
        linearLayout.setSelected(true);
    }


    private long exitTime;
    @Override
    public void onBackPressed() {
        //显示在：发现Fragment
        long nowTime = System.currentTimeMillis();
        if((nowTime - exitTime) <= 2000){
            AppManager.getAppManager().AppExit(this);
        }else{
            ToastUtils.showLong("再按一次程序！");
            exitTime = nowTime;
        }
    }
}
