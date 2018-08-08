package com.dfwr.zhuanke.zhuanke.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.bean.RankBean;
import com.dfwr.zhuanke.zhuanke.util.GlideUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ckx on 2018/7/12.
 */


public class RankListAdapter extends BaseQuickAdapter<RankBean, BaseViewHolder> {
    private String type;

    public String getType() {
        return type == null ? "" : type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RankListAdapter(@Nullable List<RankBean> data, String type) {
        super(R.layout.item_rank_list, data);
        this.type = type;
    }


    @Override
    protected void convert(BaseViewHolder helper, RankBean item) {
        int layoutPosition = helper.getLayoutPosition();
        switch (layoutPosition){
            case 0:
                helper.setGone(R.id.tv_position, false);
                helper.setGone(R.id.iv_position, true);
                helper.setImageResource(R.id.iv_position,R.mipmap.first);
                break;
            case 1:
                helper.setGone(R.id.tv_position, false);
                helper.setGone(R.id.iv_position, true);
                helper.setImageResource(R.id.iv_position,R.mipmap.second);
                break;
            case 2:
                helper.setGone(R.id.tv_position, false);
                helper.setGone(R.id.iv_position, true);
                helper.setImageResource(R.id.iv_position,R.mipmap.third);
                break;
            default:
                helper.setGone(R.id.tv_position, true);
                helper.setGone(R.id.iv_position, false);
                helper.setText(R.id.tv_position,helper.getLayoutPosition()+1+"");
                break;
        }

        if (type.equals("收入排行榜")){
            helper.setText(R.id.tv_money,item.getTotalMoney()+"元");
        }else{ //收徒排行榜
            helper.setText(R.id.tv_money,item.getNum()+"个");
        }
        helper.setText(R.id.tv_name,item.getWxName());

        CircleImageView view = helper.getView(R.id.iv_head);
        GlideUtil.getInstance().loadHeadImage(helper.getConvertView().getContext(),view,item.getImgId(),true);
    }
}