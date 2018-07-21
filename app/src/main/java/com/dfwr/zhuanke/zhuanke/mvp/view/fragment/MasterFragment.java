package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.adapter.HomeAdapter;
import com.dfwr.zhuanke.zhuanke.base.BaseTwoFragment;
import com.dfwr.zhuanke.zhuanke.bean.HomeBean;
import com.dfwr.zhuanke.zhuanke.bean.UserBaseInfo;
import com.dfwr.zhuanke.zhuanke.mvp.contract.IHomeView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.HomePresent;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesTool;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ckx on 2018/7/12.
 */
public class MasterFragment extends BaseTwoFragment<IHomeView,HomePresent<IHomeView>> implements IHomeView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.tv_today_account)
    TextView tvTodayStudentNum;
    @BindView(R.id.tv_all_pupil)
    TextView tvAllPupil;
    @BindView(R.id.tv_today_pupil)
    TextView tvTodayStudentProfit;
    @BindView(R.id.tv_all__pupil_account)
    TextView tvAllStudentPofit;




    private List<HomeBean> imagesAndTitles = new ArrayList<>();
    private HomeAdapter taskAdapter;


    private int[] taskStatusPics = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher
    };

    private String[] myStr = {"微信收徒", "QQ收徒", "二维码收徒", "链接收徒"};


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_master;
    }

    @Override
    protected void fragmentToUserVisible() {
        super.fragmentToUserVisible();
        getUserData();
    }

    public void getUserData() {
        mPresent.getUserInfo();
    }


    @Override
    protected void initData() {
        super.initData();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                getUserData();
            }
        });

        for (int i = 0; i < taskStatusPics.length; i++) {
            HomeBean homeBean = new HomeBean(myStr[i], taskStatusPics[i]);
            imagesAndTitles.add(homeBean);
        }

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        taskAdapter = new HomeAdapter(imagesAndTitles);
        recyclerView.setAdapter(taskAdapter);
        getUserData();
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



    @Override
    public void getUserInfo(UserBaseInfo userBaseInfo) {
        tvAllPupil.setText(userBaseInfo.getStudentNum()+"");
        tvTodayStudentNum.setText(userBaseInfo.getTodayStudentNum()+"");
        tvTodayStudentProfit.setText(userBaseInfo.getTodayStudentPofit()+"");
        tvAllStudentPofit.setText(userBaseInfo.getStudentPofit()+"");
        SharedPreferencesUtil.putStringData(getActivity(), SharedPreferencesTool.balance,userBaseInfo.getAccount().getBalance()+"");
    }


}
