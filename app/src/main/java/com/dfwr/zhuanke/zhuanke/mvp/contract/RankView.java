package com.dfwr.zhuanke.zhuanke.mvp.contract;


import com.dfwr.zhuanke.zhuanke.base.BaseView;
import com.dfwr.zhuanke.zhuanke.bean.RankBean;

import java.util.List;

/**
 * Created by wy on 2017/12/10.
 */

public interface RankView extends BaseView {

    void getProfitRankingSuccess(List<RankBean> rankBean);

    void getStudentRankingSuccess(List<RankBean> rankBean);

}
