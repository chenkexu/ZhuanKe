package com.dfwr.zhuanke.zhuanke.mvp.contract;


import com.dfwr.zhuanke.zhuanke.base.BaseView;
import com.dfwr.zhuanke.zhuanke.bean.ProjectListData;

/**
 * Created by wy on 2017/12/10.
 */

public interface NewsListView extends BaseView {
    void getArticleListSuccess(ProjectListData projectListData);
    void getArticleListFail(String msg,boolean isNetError);

    void getArticleListRefreshSuccess(ProjectListData projectListData);
    void getArticleListRefreshError(String msg);

    void getArticleListMoreSuccess(ProjectListData projectListData);
    void getArticleListMoreFail(String errorMsg);



}
