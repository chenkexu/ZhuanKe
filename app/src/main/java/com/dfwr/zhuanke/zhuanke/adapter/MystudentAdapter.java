package com.dfwr.zhuanke.zhuanke.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.bean.MyStudentBean;
import com.dfwr.zhuanke.zhuanke.util.DateTool;
import com.dfwr.zhuanke.zhuanke.util.GlideUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ckx on 2018/7/12.
 * 收益列表
 */


public class MystudentAdapter extends BaseQuickAdapter<MyStudentBean, BaseViewHolder> {
    private int type;

    public MystudentAdapter(@Nullable List<MyStudentBean> data) {
        super(R.layout.item_rank_list, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, MyStudentBean item) {
        helper.setText(R.id.tv_name,item.getUser().getWxName());

        helper.setGone(R.id.iv_position, false);
        helper.setVisible(R.id.tv_position, true);

        helper.setText(R.id.tv_position,helper.getLayoutPosition()+1+"");

        CircleImageView view = helper.getView(R.id.iv_head);
        GlideUtil.getInstance().loadHeadImage(helper.getConvertView().getContext(),view,item.getUser().getImgId(),true);

        String date = DateTool.stampToDate(item.getCreateDate());
        helper.setText(R.id.tv_money,date+"");
    }
}