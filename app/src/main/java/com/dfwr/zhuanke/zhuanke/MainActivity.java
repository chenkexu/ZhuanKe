package com.dfwr.zhuanke.zhuanke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver;
import com.dfwr.zhuanke.zhuanke.api.param.ParamsUtil;
import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.bean.Propertie;
import com.dfwr.zhuanke.zhuanke.mvp.contract.IMsgView;
import com.dfwr.zhuanke.zhuanke.mvp.event.ChooseFragmentEvent;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.MsgPresent;
import com.dfwr.zhuanke.zhuanke.mvp.view.fragment.MasterFragment;
import com.dfwr.zhuanke.zhuanke.mvp.view.fragment.MeFragment;
import com.dfwr.zhuanke.zhuanke.mvp.view.fragment.NewsFragment;
import com.dfwr.zhuanke.zhuanke.mvp.view.fragment.WithDrawFragment;
import com.dfwr.zhuanke.zhuanke.util.AppManager;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;
import com.dfwr.zhuanke.zhuanke.widget.Dialog.AdvertisementDialog;
import com.dfwr.zhuanke.zhuanke.widget.Systems;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

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


    private Propertie propertie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //获取全局属性
        getProperties();
        showAdDialog();
    }


    //定义处理接收的方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void chooseFragment(ChooseFragmentEvent chooseStr){
        selectedFragment(0);
        tabSelected(llHome);
    }




    //获取各个单价
    public void getProperties(){
        HashMap<String, Object> map = ParamsUtil.getMap();
        ApiManager.getInstence().getApiService().getProperties(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<Propertie>>rxSchedulerHelper())
                .subscribe(new BaseObserver<Propertie>() {
                    @Override
                    protected void onSuccees(ApiResponse<Propertie> t) {
                        if (t!=null) {
                            Propertie result = t.getResult();
                            MainActivity.this.propertie = result;
                            selectedFragment(0);
                            tabSelected(llHome);
                        }
                    }
                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        ToastUtils.showShort(errorInfo);
                    }
                });
    }



    //显示广告弹窗
    private void showAdDialog() {
        AdvertisementDialog advertisementDialog = new AdvertisementDialog(this);
        advertisementDialog.showDialog();
    }



    @Override
    public void onResume() {
        super.onResume();
        if (getIntent()!=null){
            Intent intent = getIntent();
            String stringExtra = intent.getStringExtra(Systems.from_withdraw);
            if (stringExtra!=null) {
                switch (stringExtra){
                    case "0":
                        selectedFragment(0);
                        tabSelected(llHome);
                        break;
                    case "1":
                        break;
                }
            }

        }
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
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Systems.propertie,propertie);
                    newsFragment.setArguments(bundle);
                    transaction.add(R.id.content, newsFragment);
                } else
                    transaction.show(newsFragment);
                break;
            case 1:
                if (masterFragment == null) {
                    masterFragment = new MasterFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Systems.propertie,propertie);
                    masterFragment.setArguments(bundle);
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
        transaction.commitAllowingStateLoss();
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
            ToastUtils.showShort("再按一次程序！");
            exitTime = nowTime;
        }
    }



    @Override
    protected MsgPresent<IMsgView> createPresent() {
        return new MsgPresent<>(this);
    }


    @Override
    public void showLoading() {
        showDefaultLoading();
    }

    @Override
    public void hideLoading() {
        hideDefaultLoading();
    }
}
