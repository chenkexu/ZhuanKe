package com.dfwr.zhuanke.zhuanke.mvp.contract;


import com.dfwr.zhuanke.zhuanke.base.BaseView;
import com.dfwr.zhuanke.zhuanke.bean.MyStudentBean;

import java.util.List;

/**
 * Created by wy on 2017/12/10.
 * 收益
 */

public interface MyStudentView extends BaseView {

    void getStudentList(List<MyStudentBean> projectListData);
    void getStudentListError(String msg);

    void getStudentListLoadMoreSuccess(List<MyStudentBean> projectListData);
    void getStudentListLoadMoreFail(String errorMsg);
}
