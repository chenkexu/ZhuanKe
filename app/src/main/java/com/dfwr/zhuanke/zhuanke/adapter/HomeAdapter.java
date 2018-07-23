package com.dfwr.zhuanke.zhuanke.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.bean.HomeBean;

import java.util.List;

/**
 * Created by ckx on 2018/7/12.
 */


public class HomeAdapter extends BaseQuickAdapter<HomeBean, BaseViewHolder> {



    public HomeAdapter(@Nullable List<HomeBean> data) {
        super(R.layout.item_gv_me, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {
        helper.setImageResource(R.id.iv_logo, item.getPic());
        helper.setText(R.id.tv_name, item.getName());
    }
}