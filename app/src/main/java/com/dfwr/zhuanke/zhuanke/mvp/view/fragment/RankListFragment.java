package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.adapter.RankListAdapter;
import com.dfwr.zhuanke.zhuanke.base.LazyLoadFragment;
import com.dfwr.zhuanke.zhuanke.bean.RankBean;
import com.dfwr.zhuanke.zhuanke.mvp.contract.RankView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.RankPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

/**
 * Created by ckx on 2018/7/12.
 */

public class RankListFragment extends LazyLoadFragment<RankView, RankPresent<RankView>> implements
        SwipeRefreshLayout.OnRefreshListener, RankView {

    private List<Integer> channelIds = new ArrayList<>();

    //    private int currentPage;
    private ArrayList<LazyLoadFragment> mFragments = new ArrayList<>();


    RecyclerView recyclerView;

    SwipeRefreshLayout refreshLayout;

    RankListAdapter newsAdapter;
    RelativeLayout activityGuide;
    Unbinder unbinder;
    private List<RankBean> mData = new ArrayList<>();
    private int currentPage;
    private int cid;
    private static final int PAGE_SIZE = 15;




    @Override
    protected int setLayoutId() {
        return R.layout.fragment_rank_list;
    }


    public static RankListFragment getInstance() {
        RankListFragment sf = new RankListFragment();
        return sf;
    }



    @Override
    protected RankPresent createPresent() {
        return new RankPresent<>(this);
    }



    @Override
    protected void lazyLoad() {
        onRefresh();
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
    public void onRefresh() {
        String title = getTitle();
        if (title.equals("收入排行榜")) {
            mPresent.getProfitRanking();
        }else{
            mPresent.getStudentRanking();
        }
    }



    private void initView() {
        mData = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        refreshLayout = findViewById(R.id.refreshLayout);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout.setOnRefreshListener(this);//刷新
        newsAdapter = new RankListAdapter(mData,"1");
        newsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        recyclerView.setAdapter(newsAdapter);

    }


}
