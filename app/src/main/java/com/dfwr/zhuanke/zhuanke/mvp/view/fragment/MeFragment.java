package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import com.dfwr.zhuanke.zhuanke.bean.UserBean;
import com.dfwr.zhuanke.zhuanke.mvp.contract.IHomeView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.HomePresent;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.BusinessHezuoActivity;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.RankActivity;
import com.dfwr.zhuanke.zhuanke.util.GlideUtil;
import com.dfwr.zhuanke.zhuanke.util.UserDataManeger;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ckx on 2018/7/12.
 * <p>
 * 我的界面
 */

public class MeFragment extends BaseTwoFragment<IHomeView,HomePresent<IHomeView>> implements IHomeView {


//    @BindView(R.id.tv_name)
//    TextView tvName;

    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_all_pupil)
    TextView tvAllPupil;
    @BindView(R.id.tv_today_profit)
    TextView tvTodayProfit;
    @BindView(R.id.tv_today_pupil)
    TextView tvTodayPupil;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_add_qq)
    TextView tvAddQq;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_artical_money)
    TextView tvArticalMoney;
    @BindView(R.id.tl_5)
    SlidingTabLayout tl5;
    @BindView(R.id.vp)
    ViewPager viewPager;



    Unbinder unbinder;
    private List<HomeBean> imagesAndTitles = new ArrayList<>();
    private HomeAdapter taskAdapter;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {
            "文章收益", "徒弟分享收益", "收徒收益"
    };



    private MyPagerAdapter mAdapter;

    private int[] taskStatusPics = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher
    };

    private String[] myStr = {"开始赚钱", "排行榜", "攻略",
            "商务合作"};


    @Override
    public void getUserInfo(UserBaseInfo userBaseInfo) {
        tvAccount.setText("余额（元）："+userBaseInfo.getAccount().getBalance());
        tvAllPupil.setText("总收徒（人）："+userBaseInfo.getStudentNum());
        tvTodayProfit.setText(userBaseInfo.getAccount().getTodayMoney()+"");
        tvArticalMoney.setText(userBaseInfo.getAccount().getArticleMoney()+"");
        tvTodayPupil.setText(userBaseInfo.getTodayStudentNum()+"");
    }



    @Override
    protected int setLayoutId() {
        return R.layout.fragment_me;
    }


    @Override
    protected void initView() {
        super.initView();
        UserBean userBean = UserDataManeger.getInstance().getUserBean();

        if (userBean != null) {
            GlideUtil.getInstance().loadHeadImage(getActivity(),ivHead,userBean.getUser().getImgId(),true);
            tvId.setText("ID:"+userBean.getUser().getUid());
        }

        for (int i = 0; i < taskStatusPics.length; i++) {
            HomeBean homeBean = new HomeBean(myStr[i], taskStatusPics[i]);
            imagesAndTitles.add(homeBean);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        taskAdapter = new HomeAdapter(imagesAndTitles);
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), RankActivity.class));
                        break;
                    case 2:
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), BusinessHezuoActivity.class));
                        break;
                }

            }
        });
        initViewPager();
    }

    @Override
    protected void initData() {
        super.initData();
        setData();

    }

    @Override
    protected void fragmentToUserVisible() {
        super.fragmentToUserVisible();
        setData();
    }

    private void setData() {
        mPresent.getUserInfo();
    }


    private void initViewPager() {
        for (String title : mTitles) {
            MeProfitFragment fragment = MeProfitFragment.getInstance();
            fragment.setTitle(title);
            mFragments.add(fragment);
        }
        mAdapter = new MyPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(0);
        tl5.setViewPager(viewPager);
    }






















    @Override
    protected HomePresent<IHomeView> createPresent() {
        return new HomePresent<>(this);
    }

    @OnClick(R.id.tv_add_qq)
    public void onViewClicked() {
        joinQQGroup("qDmYFkk2XtU903xEk2vrPayicCb-lAeh");
    }

    /****************
     * 发起添加群流程。群号：乐享转官方群(826248193) 的 key 为： qDmYFkk2XtU903xEk2vrPayicCb-lAeh
     * 调用 joinQQGroup(qDmYFkk2XtU903xEk2vrPayicCb-lAeh) 即可发起手Q客户端申请加群 乐享转官方群(826248193)
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
     ******************/
    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }

    @Override
    public void showLoading() {
        showDefaultLoading();
    }

    @Override
    public void hideLoading() {
        hideDefaultLoading();
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
