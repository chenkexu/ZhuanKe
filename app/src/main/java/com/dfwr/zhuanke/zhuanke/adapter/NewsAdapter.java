package com.dfwr.zhuanke.zhuanke.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.bean.Article;
import com.dfwr.zhuanke.zhuanke.util.GlideUtil;

import java.util.List;


/**
 * @author quchao
 * @date 2018/2/24
 */

public class NewsAdapter extends BaseQuickAdapter<Article, BaseViewHolder> {
    private double price;

    public void setPrice(String price){
        double v = Double.parseDouble(price);
        this.price = v * 10;
        notifyDataSetChanged();
    }

    public NewsAdapter(@Nullable List<Article> data) {
        super(R.layout.item_news_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Article item) {
        if (!TextUtils.isEmpty(item.getHeadImg())) {
            GlideUtil.getInstance().loadAdImage(mContext, (ImageView) helper.getView(R.id.item_project_list_iv),item.getHeadImg()
            ,true);
        }
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.item_project_list_title_tv, item.getTitle());
        }

        helper.setText(R.id.tv_price, "点击分享赚"+price+"毛");
        helper.setText(R.id.tv_watch_sum, item.getClick()+"分享");
    }


}
