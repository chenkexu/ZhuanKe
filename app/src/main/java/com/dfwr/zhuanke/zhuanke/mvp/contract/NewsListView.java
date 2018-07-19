package com.dfwr.zhuanke.zhuanke.mvp.contract;


import com.dfwr.zhuanke.zhuanke.base.BaseView;
import com.dfwr.zhuanke.zhuanke.bean.Article;

import java.util.List;

/**
 * Created by wy on 2017/12/10.
 */

public interface NewsListView extends BaseView {
    void getArticleListSuccess(List<Article> projectListData);
    void getArticleListRefreshError(String msg);

    void getArticleListMoreSuccess(List<Article> projectListData);
    void getArticleListMoreFail(String errorMsg);



}
