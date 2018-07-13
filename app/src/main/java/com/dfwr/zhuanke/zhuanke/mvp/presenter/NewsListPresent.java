package com.dfwr.zhuanke.zhuanke.mvp.presenter;


import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver2;
import com.dfwr.zhuanke.zhuanke.api.response.BaseResponse;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.ProjectListData;
import com.dfwr.zhuanke.zhuanke.mvp.contract.NewsListView;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;

/**
 * Created by wy on 2017/12/5.
 * 消息
 */

public class NewsListPresent<T> extends BasePresenter<NewsListView> {
    private NewsListView mMsgView;

    public NewsListPresent(NewsListView mMsgView) {
        this.mMsgView = mMsgView;
    }

    @Override
    public void fecth() {

    }





    public void getProjectListData(int page, int cid){
        mMsgView.showLoading();
        ApiManager.getInstence().getApiService().getProjectListData(page,cid)
                .compose(RxUtil.<BaseResponse<ProjectListData>>rxSchedulerHelper())
                .subscribe(new BaseObserver2<ProjectListData>() {
                    @Override
                    protected void onSuccees(BaseResponse<ProjectListData> t) {
                        mMsgView.hideLoading();
                        mMsgView.getArticleListRefreshSuccess(t.getData());
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        mMsgView.hideLoading();
                        mMsgView.getArticleListRefreshError(errorInfo);
                        ToastUtils.showShort(errorInfo);
                    }
                });
    }

    public void getProjectListLoadMore(int page, int cid){
        ApiManager.getInstence().getApiService().getProjectListData(page,cid)
                .compose(RxUtil.<BaseResponse<ProjectListData>>rxSchedulerHelper())
                .subscribe(new BaseObserver2<ProjectListData>() {
                    @Override
                    protected void onSuccees(BaseResponse<ProjectListData> t) {
                        mMsgView.getArticleListMoreSuccess(t.getData());
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        mMsgView.getArticleListMoreFail(errorInfo);
                        ToastUtils.showShort(errorInfo);
                    }
                });
    }

}
