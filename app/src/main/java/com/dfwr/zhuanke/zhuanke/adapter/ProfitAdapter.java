package com.dfwr.zhuanke.zhuanke.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.bean.MyProfit;
import com.dfwr.zhuanke.zhuanke.util.DateTool;

import java.util.List;

/**
 * Created by ckx on 2018/7/12.
 * 收益列表
 */


public class ProfitAdapter extends BaseQuickAdapter<MyProfit, BaseViewHolder> {
    private int type;

    public ProfitAdapter(@Nullable List<MyProfit> data,int type) {
        super(R.layout.item_make_money, data);
        this.type = type;
    }


    @Override
    protected void convert(BaseViewHolder helper, MyProfit item) {
        switch (type) {
            case 1: //文章收益
                helper.setText(R.id.tv_name, helper.getLayoutPosition()+1+"."+item.getArticle().getTitle());
                break;
            case 2: //收徒收益
                helper.setText(R.id.tv_name, helper.getLayoutPosition()+1+"."+item.getUser().getWxName());
                break;
            case 3: //徒弟分享收益
                helper.setText(R.id.tv_name, helper.getLayoutPosition()+1+"."+item.getUser().getWxName());
                break;
        }
        String date = DateTool.stampToDate(item.getCreateDate());
        helper.setText(R.id.tv_date,date+"");
        helper.setText(R.id.tv_money, "+"+item.getMoney()+"元");
    }
}