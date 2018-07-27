package com.dfwr.zhuanke.zhuanke.mvp.presenter;


import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver;
import com.dfwr.zhuanke.zhuanke.api.param.ParamsUtil;
import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.MyProfit;
import com.dfwr.zhuanke.zhuanke.mvp.contract.ProfitView;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wy on 2017/12/5.
 * 消息
 */

public class ProfitPresent<T> extends BasePresenter<ProfitView> {

    private ProfitView profitView;

    public ProfitPresent(ProfitView mMsgView) {
        this.profitView = mMsgView;
    }

    @Override
    public void fecth() {

    }


    //获取提现记录
    public void getProfitList(int page,int size,int type){
        HashMap<String, Object> map = ParamsUtil.getMap();
        map.put("page",page+"");
        map.put("size",size+"");
        map.put("type",type+"");
        ApiManager.getInstence().getApiService().getMyProfit(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<List<MyProfit>>>rxSchedulerHelper())
                .subscribe(new BaseObserver<List<MyProfit>>() {
                    @Override
                    protected void onSuccees(ApiResponse<List<MyProfit>> t) {
                        if (t!=null) {
                            profitView.getProfitList(t.getResult());
                        }
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        ToastUtils.showShort(errorInfo);
                        profitView.getProfitListError(errorInfo);
                    }
                });

    }



    //获取更多收益记录
    public void getProfitListLoadMore(int page,int size,int type){
        HashMap<String, Object> map = ParamsUtil.getMap();
        map.put("page",page+"");
        map.put("size",size+"");
        map.put("type",type+"");
        ApiManager.getInstence().getApiService().getMyProfit(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<List<MyProfit>>>rxSchedulerHelper())
                .subscribe(new BaseObserver<List<MyProfit>>() {
                    @Override
                    protected void onSuccees(ApiResponse<List<MyProfit>> t) {
                        if (t!=null) {
                            profitView.getProfitListLoadMoreSuccess(t.getResult());
                        }
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        profitView.getProfitListLoadMoreFail(errorInfo);
                        ToastUtils.showShort(errorInfo);

                    }
                });

    }


}
