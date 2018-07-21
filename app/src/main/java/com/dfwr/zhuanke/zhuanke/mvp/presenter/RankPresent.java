package com.dfwr.zhuanke.zhuanke.mvp.presenter;


import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver;
import com.dfwr.zhuanke.zhuanke.api.param.ParamsUtil;
import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.RankBean;
import com.dfwr.zhuanke.zhuanke.mvp.contract.RankView;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wy on 2017/12/5.
 * 消息
 */

public class RankPresent<T> extends BasePresenter<RankView> {

    private RankView rankView;

    public RankPresent(RankView mMsgView) {
        this.rankView = mMsgView;
    }

    @Override
    public void fecth() {

    }


    public void getStudentRanking(){
        HashMap<String, Object> map = ParamsUtil.getMap();
        ApiManager.getInstence().getApiService().studentRanking(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<List<RankBean>>>rxSchedulerHelper())
                .subscribe(new BaseObserver<List<RankBean>>() {
                    @Override
                    protected void onSuccees(ApiResponse<List<RankBean>> t) {
                        rankView.getStudentRankingSuccess(t.getResult());
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        ToastUtils.showShort(errorInfo);

                    }
                });
    }


    public void getProfitRanking(){
        HashMap<String, Object> map = ParamsUtil.getMap();
        ApiManager.getInstence().getApiService().profitRanking(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<List<RankBean>>>rxSchedulerHelper())
                .subscribe(new BaseObserver<List<RankBean>>() {
                    @Override
                    protected void onSuccees(ApiResponse<List<RankBean>> t) {
                        rankView.getProfitRankingSuccess(t.getResult());
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        ToastUtils.showShort(errorInfo);
                    }
                });
    }


}
