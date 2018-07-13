package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.adapter.NewsAdapter;
import com.dfwr.zhuanke.zhuanke.base.LazyLoadFragment;
import com.dfwr.zhuanke.zhuanke.bean.FeedArticleData;
import com.dfwr.zhuanke.zhuanke.bean.ProjectListData;
import com.dfwr.zhuanke.zhuanke.mvp.contract.NewsListView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.NewsListPresent;
import com.dfwr.zhuanke.zhuanke.mvp.view.CommonWebView;
import com.dfwr.zhuanke.zhuanke.widget.Systems;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

/**
 * Created by ckx on 2018/6/20.
 */

public class NewsListFragment extends LazyLoadFragment<NewsListView, NewsListPresent<NewsListView>> implements
        SwipeRefreshLayout.OnRefreshListener, NewsListView, BaseQuickAdapter.RequestLoadMoreListener {

    RecyclerView recyclerView;

    SwipeRefreshLayout refreshLayout;

    NewsAdapter newsAdapter;
    RelativeLayout activityGuide;
    Unbinder unbinder;
    private List<FeedArticleData> mData = new ArrayList<>();
    private int currentPage;
    private int cid;
    private static final int PAGE_SIZE = 15;

    @Override
    public void showLoading() {
        showDefaultLoading();
    }

    @Override
    public void hideLoading() {
        hideDefaultLoading();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_news_list;
    }

    @Override
    protected NewsListPresent<NewsListView> createPresent() {
        return new NewsListPresent<>(this);
    }


    @Override
    protected void lazyLoad() {
        initView();
        initData();
    }

    private void initData() {
        onRefresh();
        newsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<FeedArticleData> data = newsAdapter.getData();
                FeedArticleData feedArticleData = data.get(position);
                Intent intent = new Intent(getActivity(), CommonWebView.class);
                intent.putExtra(Systems.title, feedArticleData.getTitle());
                intent.putExtra(Systems.url, feedArticleData.getProjectLink());

                intent.putExtra(Systems.feedArticleData, feedArticleData);

                startActivity(intent);
            }
        });
    }

    private void initView() {
        mData = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        refreshLayout = findViewById(R.id.refreshLayout);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout.setOnRefreshListener(this);//刷新
        newsAdapter = new NewsAdapter(mData);
        newsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        newsAdapter.setOnLoadMoreListener(this, recyclerView);

        recyclerView.setAdapter(newsAdapter);
        Bundle bundle = getArguments();
        cid = bundle.getInt(Systems.ARG_PARAM1);
    }


    //下拉刷新
    @Override
    public void onRefresh() {
        currentPage = 1;
        newsAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        mPresent.getProjectListData(currentPage, cid);
    }


    //加载更多
    @Override
    public void onLoadMoreRequested() {
        mPresent.getProjectListLoadMore(currentPage, cid);
    }


    private void setData(boolean isRefresh, List data) {
        currentPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            newsAdapter.setNewData(data);
        } else {
            if (size > 0) {
                newsAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            newsAdapter.loadMoreEnd(isRefresh);
        } else {
            newsAdapter.loadMoreComplete();
        }
    }


    @Override
    public void getArticleListSuccess(ProjectListData projectListData) {

    }

    @Override
    public void getArticleListFail(String msg, boolean isNetError) {

    }


    @Override
    public void getArticleListRefreshSuccess(ProjectListData projectListData) {
        mData = projectListData.getDatas();
        setData(true, mData);
        newsAdapter.setEnableLoadMore(true);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void getArticleListRefreshError(String msg) {
        newsAdapter.setEnableLoadMore(true);
        refreshLayout.setRefreshing(false);
    }


    @Override
    public void getArticleListMoreSuccess(ProjectListData projectListData) {
        mData = projectListData.getDatas();
        setData(false, mData);
    }

    @Override
    public void getArticleListMoreFail(String errorMsg) {
        newsAdapter.loadMoreFail();
    }


    public static NewsListFragment getInstance(int param1, String param2) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putInt(Systems.ARG_PARAM1, param1);
        args.putString(Systems.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


}
