package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageView;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BaseTwoFragment;
import com.dfwr.zhuanke.zhuanke.base.LazyLoadFragment;
import com.dfwr.zhuanke.zhuanke.bean.ProjectClassifyData;
import com.dfwr.zhuanke.zhuanke.mvp.contract.NewsView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.NewsPresent;
import com.dfwr.zhuanke.zhuanke.widget.Systems;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ckx on 2018/7/12.
 */

public class NewsFragment extends BaseTwoFragment<NewsView, NewsPresent<NewsView>>
        implements NewsView {


    @BindView(R.id.add_channel_iv)
    ImageView addChannelIv;

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tl_5)
    SlidingTabLayout mTabLayout;
    private List<ProjectClassifyData> mData;
//    private int currentPage;

    private ArrayList<LazyLoadFragment> mFragments = new ArrayList<>();




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
//        Intent intent = getActivity().getIntent();
//        String newsType = intent.getStringExtra(Systems.newsType);
//
//        if (newsType!=null) {
//            int positon = Arrays.binarySearch(mTitles, newsType);
//            viewPager.setCurrentItem(positon);
//        }else{
//            viewPager.setCurrentItem(0);
//        }
        mPresent.getProjectClassifyData();
    }

    @Override
    protected NewsPresent createPresent() {
        return new NewsPresent<>(this);
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
        initViewPagerAndTabLayout();
    }



    private void initViewPagerAndTabLayout() {
        Log.d("okgo", "接收到了数据");
        for (ProjectClassifyData data : mData) {
            NewsListFragment projectListFragment = NewsListFragment.getInstance(data.getId(), null);
            mFragments.add(projectListFragment);
        }
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mData == null? 0 : mData.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mData.get(position).getName();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.setViewPager(viewPager);
        viewPager.setCurrentItem(Systems.TAB_ONE);
    }


}
