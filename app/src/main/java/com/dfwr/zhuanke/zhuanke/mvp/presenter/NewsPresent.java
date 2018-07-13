package com.dfwr.zhuanke.zhuanke.mvp.presenter;


import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver2;
import com.dfwr.zhuanke.zhuanke.api.response.BaseResponse;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.ProjectClassifyData;
import com.dfwr.zhuanke.zhuanke.mvp.contract.NewsView;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;

import java.util.List;

/**
 * Created by wy on 2017/12/5.
 * 消息
 */

public class NewsPresent<T> extends BasePresenter<NewsView> {
    private NewsView mMsgView;

    public NewsPresent(NewsView mMsgView) {
        this.mMsgView = mMsgView;
    }

    @Override
    public void fecth() {

    }



    public void getProjectClassifyData(){
        ApiManager.getInstence().getApiService().getProjectClassifyData()
                .compose(RxUtil.<BaseResponse<List<ProjectClassifyData>>>rxSchedulerHelper())
                .subscribe(new BaseObserver2<List<ProjectClassifyData>>() {
                    @Override
                    protected void onSuccees(BaseResponse<List<ProjectClassifyData>> t) {
                        if (t.getData()!=null) {
                            mMsgView.getProjectClassifyDataSuccess(t.getData());
                        }
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {

                    }
                });
    }




}
