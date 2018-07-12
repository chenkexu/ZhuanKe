package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.adapter.NewsAdapter;
import com.dfwr.zhuanke.zhuanke.base.LazyLoadFragment;
import com.dfwr.zhuanke.zhuanke.bean.FeedArticleData;
import com.dfwr.zhuanke.zhuanke.bean.ProjectListData;
import com.dfwr.zhuanke.zhuanke.mvp.contract.NewsView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.NewsPresent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ckx on 2018/6/20.
 */

public class NewsListFragment extends LazyLoadFragment<NewsView, NewsPresent<NewsView>> implements
        SwipeRefreshLayout.OnRefreshListener, NewsView ,BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    NewsAdapter newsAdapter;
    private List<FeedArticleData> feedArticleDatas;



    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected NewsPresent<NewsView> createPresent() {
        return new NewsPresent<>(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_news_list;
    }

    @Override
    protected void lazyLoad() {
        initView();
    }

    private void initView() {
        feedArticleDatas = new ArrayList<>();
        newsAdapter = new NewsAdapter(feedArticleDatas);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout.setOnRefreshListener(this);//刷新
        newsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        newsAdapter.setOnLoadMoreListener(this,recyclerView);
    }



    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }


    @Override
    public void getArticleListSuccess(ProjectListData projectListData) {

    }

    @Override
    public void getArticleListFail(String msg, boolean isNetError) {

    }

    @Override
    public void getArticleListRefreshSuccess(ProjectListData projectListData) {

    }

    @Override
    public void getArticleListRefreshError(String msg) {

    }

    @Override
    public void getArticleListMoreSuccess(ProjectListData projectListData) {

    }

    @Override
    public void getArticleListMoreFail(String errorMsg) {

    }
}
