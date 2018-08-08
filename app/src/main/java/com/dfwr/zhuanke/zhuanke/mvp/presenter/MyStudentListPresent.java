package com.dfwr.zhuanke.zhuanke.mvp.presenter;


import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver;
import com.dfwr.zhuanke.zhuanke.api.param.ParamsUtil;
import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.MyStudentBean;
import com.dfwr.zhuanke.zhuanke.mvp.contract.MyStudentView;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wy on 2017/12/5.
 * 消息
 */

public class MyStudentListPresent<T> extends BasePresenter<MyStudentView> {

    private MyStudentView profitView;

    public MyStudentListPresent(MyStudentView mMsgView) {
        this.profitView = mMsgView;
    }

    @Override
    public void fecth() {

    }



    //获取我的徒弟
    public void getMyStudent(int page,int size,String type){
        HashMap<String, Object> map = ParamsUtil.getMap();
        map.put("page",page+"");
        map.put("size",size+"");

        if (type.equals("all")) {
            ApiManager.getInstence().getApiService().all_student_count(ParamsUtil.getParams(map))
                    .compose(RxUtil.<ApiResponse<List<MyStudentBean>>>rxSchedulerHelper())
                    .subscribe(new BaseObserver<List<MyStudentBean>>() {
                        @Override
                        protected void onSuccees(ApiResponse<List<MyStudentBean>> t) {
                            if (t.getResult()!=null) {
                                profitView.getStudentList(t.getResult());
                            }
                        }

                        @Override
                        protected void onFailure(String errorInfo, boolean isNetWorkError) {
                            ToastUtils.showShort(errorInfo);
                            profitView.getStudentListError(errorInfo);
                        }
                    });
        }else{
            ApiManager.getInstence().getApiService().today_student_count(ParamsUtil.getParams(map))
                    .compose(RxUtil.<ApiResponse<List<MyStudentBean>>>rxSchedulerHelper())
                    .subscribe(new BaseObserver<List<MyStudentBean>>() {
                        @Override
                        protected void onSuccees(ApiResponse<List<MyStudentBean>> t) {
                            if (t.getResult()!=null) {
                                profitView.getStudentList(t.getResult());
                            }
                        }

                        @Override
                        protected void onFailure(String errorInfo, boolean isNetWorkError) {
                            ToastUtils.showShort(errorInfo);
                            profitView.getStudentListError(errorInfo);
                        }
                    });
        }




    }






    public void getMyStudentLoadMore(int page,int size,String type){
        HashMap<String, Object> map = ParamsUtil.getMap();
        map.put("page",page+"");
        map.put("size",size+"");


        if (type.equals("all")) {
            ApiManager.getInstence().getApiService().all_student_count(ParamsUtil.getParams(map))
                    .compose(RxUtil.<ApiResponse<List<MyStudentBean>>>rxSchedulerHelper())
                    .subscribe(new BaseObserver<List<MyStudentBean>>() {
                        @Override
                        protected void onSuccees(ApiResponse<List<MyStudentBean>> t) {
                            if (t.getResult()!=null) {
                                profitView.getStudentListLoadMoreSuccess(t.getResult());
                            }
                        }

                        @Override
                        protected void onFailure(String errorInfo, boolean isNetWorkError) {
                            profitView.getStudentListLoadMoreFail(errorInfo);
                            ToastUtils.showShort(errorInfo);
                        }
                    });
        }else{
            ApiManager.getInstence().getApiService().today_student_count(ParamsUtil.getParams(map))
                    .compose(RxUtil.<ApiResponse<List<MyStudentBean>>>rxSchedulerHelper())
                    .subscribe(new BaseObserver<List<MyStudentBean>>() {
                        @Override
                        protected void onSuccees(ApiResponse<List<MyStudentBean>> t) {
                            if (t.getResult()!=null) {
                                profitView.getStudentListLoadMoreSuccess(t.getResult());
                            }
                        }

                        @Override
                        protected void onFailure(String errorInfo, boolean isNetWorkError) {
                            profitView.getStudentListLoadMoreFail(errorInfo);
                            ToastUtils.showShort(errorInfo);
                        }
                    });
        }


    }







}
