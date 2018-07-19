package com.dfwr.zhuanke.zhuanke.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.bean.RankBean;

import java.util.List;

/**
 * Created by ckx on 2018/7/12.
 */


public class RankListAdapter extends BaseQuickAdapter<RankBean, BaseViewHolder> {

    public RankListAdapter(@Nullable List<RankBean> data,String type) {
        super(R.layout.item_gv_me, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RankBean item) {

    }
}