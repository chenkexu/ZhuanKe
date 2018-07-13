package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;

import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.base.BaseTwoFragment;

/**
 * Created by ckx on 2018/7/12.
 */

public class WithDrawFragment extends BaseTwoFragment {




    @Override
    protected int setLayoutId() {
        return R.layout.fragment_winthdraw;
    }

    @Override
    protected void initData() {
        super.initData();


    }

    @Override
    protected BasePresenter createPresent() {
        return new BasePresenter() {
            @Override
            public void fecth() {

            }
        };
    }
}
