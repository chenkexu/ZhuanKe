package com.dfwr.zhuanke.zhuanke.mvp.presenter;


import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver;
import com.dfwr.zhuanke.zhuanke.api.param.ParamsUtil;
import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.UserBaseInfo;
import com.dfwr.zhuanke.zhuanke.mvp.contract.IHomeView;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;

import java.util.HashMap;

/**
 * Created by wy on 2017/12/5.
 * 消息
 */

public class HomePresent<T> extends BasePresenter<IHomeView> {

    private IHomeView mMsgView;

    public HomePresent(IHomeView mMsgView) {
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
                        mMsgView.hideLoading();
                        if (t!=null) {
                            mMsgView.getUserInfo(t.getResult());

                        }
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        mMsgView.hideLoading();
                        ToastUtils.showShort(errorInfo);
                    }
                });

    }

    //获取收徒链接
    public void getStudentLink() {
        mMsgView.showLoading();
        HashMap<String, Object> map = ParamsUtil.getMap();
        ApiManager.getInstence().getApiService().get_student_share_url(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<String>>rxSchedulerHelper())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onSuccees(ApiResponse<String> t) {
                        mMsgView.getStudentLink(t.getResult());
                        mMsgView.hideLoading();
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        mMsgView.hideLoading();
                        ToastUtils.showShort(errorInfo);
                    }
                });
    }
















  /*  public void getProperties(){
        HashMap<String, Object> map = ParamsUtil.getMap();
        ApiManager.getInstence().getApiService().getProperties(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<Object>>rxSchedulerHelper())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    protected void onSuccees(ApiResponse<Object> t) {
                        if (t!=null) {
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
*/




}
