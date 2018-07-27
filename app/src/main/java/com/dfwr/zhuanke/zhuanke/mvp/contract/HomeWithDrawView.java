package com.dfwr.zhuanke.zhuanke.mvp.contract;


import com.dfwr.zhuanke.zhuanke.base.BaseView;
import com.dfwr.zhuanke.zhuanke.bean.CheckWithDrawBean;
import com.dfwr.zhuanke.zhuanke.bean.UserBaseInfo;
import com.dfwr.zhuanke.zhuanke.bean.WithDrawHistory;

import java.util.List;

/**
 * Created by wy on 2017/12/10.
 */

public interface HomeWithDrawView extends BaseView {

    void getUserInfo(UserBaseInfo objec);
    void getCheckWithDrawSuccess(CheckWithDrawBean object);


//    void getWithDrawHistory(List<WithDrawHistory> object);
    void getWithDrawHistorySuccess(List<WithDrawHistory> projectListData);
    void getWithDrawHistoryRefreshError(String msg);

    void getWithDrawHistoryMoreSuccess(List<WithDrawHistory> projectListData);
    void getWithDrawHistoryMoreFail(String errorMsg);



}
