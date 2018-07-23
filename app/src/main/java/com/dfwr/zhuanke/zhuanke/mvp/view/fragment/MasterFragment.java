package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.adapter.HomeAdapter;
import com.dfwr.zhuanke.zhuanke.base.BaseTwoFragment;
import com.dfwr.zhuanke.zhuanke.bean.HomeBean;
import com.dfwr.zhuanke.zhuanke.bean.UserBaseInfo;
import com.dfwr.zhuanke.zhuanke.mvp.contract.IHomeView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.HomePresent;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.LoginActivity;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.MyCodeActivity;
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
        tvAllStudentPofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
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
        taskAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        Intent intent = new Intent(getActivity(), MyCodeActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        break;
                }
            }
        });
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
