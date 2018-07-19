package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.adapter.ProfitAdapter;
import com.dfwr.zhuanke.zhuanke.base.LazyLoadFragment;
import com.dfwr.zhuanke.zhuanke.bean.ProjectClassifyData;
import com.dfwr.zhuanke.zhuanke.mvp.contract.NewsView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.NewsPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

/**
 * Created by ckx on 2018/7/12.
 * 收益列表fragment
 */

public class MeProfitFragment extends LazyLoadFragment<NewsView, NewsPresent<NewsView>> implements
        SwipeRefreshLayout.OnRefreshListener, NewsView {


    private List<Integer> channelIds = new ArrayList<>();

//    private int currentPage;
    private ArrayList<LazyLoadFragment> mFragments = new ArrayList<>();


    RecyclerView recyclerView;

    SwipeRefreshLayout refreshLayout;

    ProfitAdapter newsAdapter;
    RelativeLayout activityGuide;
    Unbinder unbinder;
    private List<ProjectClassifyData> mData = new ArrayList<>();
    private int currentPage;
    private int cid;
    private static final int PAGE_SIZE = 15;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_news_list;
    }

    public static MeProfitFragment getInstance() {
        MeProfitFragment sf = new MeProfitFragment();
        return sf;
    }


    @Override
    protected NewsPresent createPresent() {
        return new NewsPresent<>(this);
    }



    @Override
    protected void lazyLoad() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getProjectClassifyDataSuccess(List<ProjectClassifyData> projectClassifyDatas) {
        mData = projectClassifyDatas;
    }


    @Override
    public void onRefresh() {

    }

    private void initView() {
        mData = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        refreshLayout = findViewById(R.id.refreshLayout);


//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        refreshLayout.setOnRefreshListener(this);//刷新
//        newsAdapter = new ProfitAdapter(mData);
//        newsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
//        newsAdapter.setOnLoadMoreListener(this, recyclerView);
//
//        recyclerView.setAdapter(newsAdapter);
//        Bundle bundle = getArguments();
//        cid = bundle.getInt(Systems.ARG_PARAM1);
    }


}
