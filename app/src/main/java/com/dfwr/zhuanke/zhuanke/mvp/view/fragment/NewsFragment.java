package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BaseTwoFragment;
import com.dfwr.zhuanke.zhuanke.base.LazyLoadFragment;
import com.dfwr.zhuanke.zhuanke.bean.ProjectClassifyData;
import com.dfwr.zhuanke.zhuanke.mvp.contract.NewsView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.NewsPresent;
import com.dfwr.zhuanke.zhuanke.widget.Dialog.ChannelDialog;
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
    private List<Integer> channelIds = new ArrayList<>();

//    private int currentPage;

    private ArrayList<LazyLoadFragment> mFragments = new ArrayList<>();


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
        // TODO: 2018/7/18 请求新闻数据 
        mPresent.getProjectClassifyData();

    }

    @Override
    protected NewsPresent createPresent() {
        return new NewsPresent<>(this);
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
    public void getProjectClassifyDataSuccess(List<ProjectClassifyData> projectClassifyDatas) {
        mData = projectClassifyDatas;
        addChannelIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChannelDialog channelDialog = new ChannelDialog(getActivity(),mData);
                channelDialog.SetOnChannelClick(new ChannelDialog.SetOnChannelClickInterFace() {
                    @Override
                    public void itemOnclick(int position) {
                        viewPager.setCurrentItem(position);
                    }
                });
                channelDialog.show();
            }
        });
        initViewPagerAndTabLayout();
    }



    private void initViewPagerAndTabLayout() {

        for (ProjectClassifyData data : mData) {
            NewsListFragment projectListFragment = NewsListFragment.getInstance(data.getType(), null);
            mFragments.add(projectListFragment);
            channelIds.add(data.getId());
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
                return mData.get(position).getType();
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
