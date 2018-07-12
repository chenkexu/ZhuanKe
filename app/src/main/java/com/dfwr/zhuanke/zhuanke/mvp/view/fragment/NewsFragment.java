package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BaseTwoFragment;
import com.dfwr.zhuanke.zhuanke.bean.ProjectClassifyData;
import com.dfwr.zhuanke.zhuanke.widget.Systems;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ckx on 2018/7/12.
 */

public class NewsFragment extends BaseTwoFragment {


    @BindView(R.id.add_channel_iv)
    ImageView addChannelIv;

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tl_5)
    SlidingTabLayout mTabLayout;
    private List<ProjectClassifyData> mData;

    private MyPagerAdapter mAdapter;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private final String[] mTitles = {
            "全部", "审核中", "审核失败"
            , "待投放", "投放中", "投放结束"
    };

    private int currentPage;

    @Override
    protected int setLayoutId() {
        return R.layout.app_bar_news;
    }


    @Override
    protected void initData() {
        super.initData();
        mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(0);
        mTabLayout.setViewPager(viewPager);

        Intent intent = getActivity().getIntent();
        String newsType = intent.getStringExtra(Systems.newsType);

        if (newsType!=null) {
            int positon = Arrays.binarySearch(mTitles, newsType);
            viewPager.setCurrentItem(positon);
        }else{
            viewPager.setCurrentItem(0);
        }
    }




    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
