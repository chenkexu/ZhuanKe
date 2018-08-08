package com.dfwr.zhuanke.zhuanke.mvp.presenter;


import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver;
import com.dfwr.zhuanke.zhuanke.api.param.ParamsUtil;
import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.CheckWithDrawBean;
import com.dfwr.zhuanke.zhuanke.bean.UserBaseInfo;
import com.dfwr.zhuanke.zhuanke.bean.WithDrawHistory;
import com.dfwr.zhuanke.zhuanke.mvp.contract.HomeWithDrawView;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wy on 2017/12/5.
 * 消息
 */

public class HomeWithDrawPresent<T> extends BasePresenter<HomeWithDrawView> {

    private HomeWithDrawView mMsgView;

    public HomeWithDrawPresent(HomeWithDrawView mMsgView) {
        this.mMsgView = mMsgView;
    }

    @Override
    public void fecth() {

    }

    public void getUserInfo(){
        mMsgView.showLoading();
        HashMap<String, Object> map = ParamsUtil.getMap();
        ApiManager.getInstence().getApiService().getUserInfo(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<UserBaseInfo>>rxSchedulerHelper())
                .subscribe(new BaseObserver<UserBaseInfo>() {
                    @Override
                    protected void onSuccees(ApiResponse<UserBaseInfo> t) {
                        if (t!=null) {
                            mMsgView.getUserInfo(t.getResult());

                        }
                        mMsgView.hideLoading();
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        ToastUtils.showShort(errorInfo);
                        mMsgView.hideLoading();
                    }
                });

    }



    //提现校验
    public void checkWithDraw(final String type){
        mMsgView.showLoading();
        HashMap<String, Object> map = ParamsUtil.getMap();
        map.put("type",  type);
        ApiManager.getInstence().getApiService().takeMoneyCheck(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<CheckWithDrawBean>>rxSchedulerHelper())
                .subscribe(new BaseObserver<CheckWithDrawBean>() {
                    @Override
                    protected void onSuccees(ApiResponse<CheckWithDrawBean> t) {
                        if (t!=null) {
                            if (type.equals("1")) {
                                mMsgView.getCheckWithDrawWeChatSuccess(t.getResult());
                            }else{
                                mMsgView.getCheckWithDrawAlipaySuccess(t.getResult());
                            }
                        }
                        mMsgView.hideLoading();
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        ToastUtils.showShort(errorInfo);
                        mMsgView.hideLoading();
                    }
                });

    }



    //获取提现记录
    public void getWithDrawHistory(int page,int size){
        HashMap<String, Object> map = ParamsUtil.getMap();
        map.put("page",page+"");
        map.put("size",size+"");
        ApiManager.getInstence().getApiService().get_cash_record(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<List<WithDrawHistory>>>rxSchedulerHelper())
                .subscribe(new BaseObserver<List<WithDrawHistory>>() {
                    @Override
                    protected void onSuccees(ApiResponse<List<WithDrawHistory>> t) {
                        if (t!=null) {
                            mMsgView.getWithDrawHistorySuccess(t.getResult());
                        }
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        ToastUtils.showShort(errorInfo);
                        mMsgView.getWithDrawHistoryRefreshError(errorInfo);
                    }
                });

    }



    //获取提现记录
    public void getWithDrawHistoryLoadMore(int page,int size){
        HashMap<String, Object> map = ParamsUtil.getMap();
        map.put("page",page+"");
        map.put("size",size+"");
        ApiManager.getInstence().getApiService().get_cash_record(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<List<WithDrawHistory>>>rxSchedulerHelper())
                .subscribe(new BaseObserver<List<WithDrawHistory>>() {
                    @Override
                    protected void onSuccees(ApiResponse<List<WithDrawHistory>> t) {
                        if (t!=null) {
                            mMsgView.getWithDrawHistoryMoreSuccess(t.getResult());
                        }
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        mMsgView.getWithDrawHistoryMoreFail(errorInfo);
                        ToastUtils.showShort(errorInfo);

                    }
                });

    }





}
