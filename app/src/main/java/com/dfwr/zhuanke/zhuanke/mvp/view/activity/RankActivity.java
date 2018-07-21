package com.dfwr.zhuanke.zhuanke.mvp.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.adapter.HomeAdapter;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.HomeBean;
import com.dfwr.zhuanke.zhuanke.mvp.view.fragment.RankListFragment;
import com.dfwr.zhuanke.zhuanke.widget.MyTitle;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ckx on 2018/7/17.
 */

public class RankActivity extends BaseActivity {


    @BindView(R.id.my_title)
    MyTitle myTitle;
    @BindView(R.id.tl_5)
    SlidingTabLayout tl5;
    @BindView(R.id.vp)
    ViewPager viewPager;

    private List<HomeBean> imagesAndTitles = new ArrayList<>();
    private HomeAdapter taskAdapter;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "收入排行榜", "收徒排行榜"
    };
    private MyPagerAdapter mAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        ButterKnife.bind(this);
        initView();
        initData();
    }




    private void initViewPager() {
        for (String title : mTitles) {
            RankListFragment fragment = RankListFragment.getInstance();
            fragment.setTitle(title);
            mFragments.add(fragment);
        }
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(0);
        tl5.setViewPager(viewPager);
    }



    private void initView() {
        myTitle.setImageBack(this);
        myTitle.setTitleName("排行榜");
        initViewPager();
    }


    private void initData() {


    }







    @Override
    protected BasePresenter createPresent() {
        return new BasePresenter() {
            @Override
            public void fecth() {

            }
        };
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
