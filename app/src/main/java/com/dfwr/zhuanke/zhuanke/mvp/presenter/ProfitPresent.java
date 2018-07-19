package com.dfwr.zhuanke.zhuanke.mvp.presenter;


import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.mvp.contract.ProfitView;

/**
 * Created by wy on 2017/12/5.
 * 消息
 */

public class ProfitPresent<T> extends BasePresenter<ProfitView> {

    private ProfitView rankView;

    public ProfitPresent(ProfitView mMsgView) {
        this.rankView = mMsgView;
    }

    @Override
    public void fecth() {

    }


//    private void getStudentRanking(){
//        HashMap<String, Object> map = ParamsUtil.getMap();
//        ApiManager.getInstence().getApiService().studentRanking(ParamsUtil.getParams(map))
//                .compose(RxUtil.<ApiResponse<Object>>rxSchedulerHelper())
//                .subscribe(new BaseObserver<Object>() {
//                    @Override
//                    protected void onSuccees(ApiResponse<Object> t) {
//
//                    }
//
//                    @Override
//                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
//                        ToastUtils.showShort(errorInfo);
//
//                    }
//                });
//    }
//
//
//    private void getProfitRanking(){
//        HashMap<String, Object> map = ParamsUtil.getMap();
//        ApiManager.getInstence().getApiService().profitRanking(ParamsUtil.getParams(map))
//                .compose(RxUtil.<ApiResponse<Object>>rxSchedulerHelper())
//                .subscribe(new BaseObserver<Object>() {
//                    @Override
//                    protected void onSuccees(ApiResponse<Object> t) {
//
//                    }
//
//                    @Override
//                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
//                        ToastUtils.showShort(errorInfo);
//
//                    }
//                });
//    }


}
