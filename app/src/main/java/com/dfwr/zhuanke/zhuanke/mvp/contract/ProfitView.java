package com.dfwr.zhuanke.zhuanke.mvp.contract;


import com.dfwr.zhuanke.zhuanke.base.BaseView;
import com.dfwr.zhuanke.zhuanke.bean.MyProfit;

import java.util.List;

/**
 * Created by wy on 2017/12/10.
 * 收益
 */

public interface ProfitView extends BaseView {



    void getProfitList(List<MyProfit> projectListData);
    void getProfitListError(String msg);

    void getProfitListLoadMoreSuccess(List<MyProfit> projectListData);
    void getProfitListLoadMoreFail(String errorMsg);



}
