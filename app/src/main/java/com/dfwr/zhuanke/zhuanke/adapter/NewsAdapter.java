package com.dfwr.zhuanke.zhuanke.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.bean.FeedArticleData;

import java.util.List;


/**
 * @author quchao
 * @date 2018/2/24
 */

public class NewsAdapter extends BaseQuickAdapter<FeedArticleData, BaseViewHolder> {

    public NewsAdapter(@Nullable List<FeedArticleData> data) {
        super(R.layout.item_news_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeedArticleData item) {

    }


}
