package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.adapter.HomeAdapter;
import com.dfwr.zhuanke.zhuanke.base.BaseTwoFragment;
import com.dfwr.zhuanke.zhuanke.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ckx on 2018/7/12.
 */

public class MasterFragment extends BaseTwoFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
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
    protected void initData() {
        super.initData();
        for (int i = 0; i < taskStatusPics.length; i++) {
            HomeBean homeBean = new HomeBean(myStr[i], taskStatusPics[i]);
            imagesAndTitles.add(homeBean);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        taskAdapter = new HomeAdapter(imagesAndTitles);
        recyclerView.setAdapter(taskAdapter);
    }
}
