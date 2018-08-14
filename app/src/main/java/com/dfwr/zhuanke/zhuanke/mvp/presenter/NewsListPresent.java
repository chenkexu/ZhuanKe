package com.dfwr.zhuanke.zhuanke.mvp.presenter;


import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver;
import com.dfwr.zhuanke.zhuanke.api.param.ParamsUtil;
import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.Article;
import com.dfwr.zhuanke.zhuanke.mvp.contract.NewsListView;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wy on 2017/12/5.
 * 新闻
 */

public class NewsListPresent<T> extends BasePresenter<NewsListView> {
    private NewsListView mMsgView;

    public NewsListPresent(NewsListView mMsgView) {
        this.mMsgView = mMsgView;
    }

    @Override
    public void fecth() {

    }


    //获取文章价格
    public void getSharePrice(){
        mMsgView.showLoading();
        HashMap<String, Object> map = ParamsUtil.getMap();
        ApiManager.getInstence().getApiService().getSharePrice(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<String>>rxSchedulerHelper())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onSuccees(ApiResponse<String> t) {

                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {

                    }
                });
    }



    public void getProjectListData(String type,int page, int rows){
        HashMap<String, Object> map = ParamsUtil.getMap();
        map.put("page",page+"");
        map.put("type",type);
        map.put("rows",rows+"");
        ApiManager.getInstence().getApiService().getArticleDetailsList(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<List<Article>>>rxSchedulerHelper())
                .subscribe(new BaseObserver<List<Article>>() {
                    @Override
                    protected void onSuccees(ApiResponse<List<Article>> t) {
                        mMsgView.hideLoading();
                        if (t.getResult()!=null) {
                            mMsgView.getArticleListSuccess(t.getResult());
                        }
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        mMsgView.hideLoading();
                        mMsgView.getArticleListRefreshError(errorInfo);
                        ToastUtils.showShort(errorInfo);
                    }
                });

    }



    public void getProjectListLoadMore(String type,int page, int rows){
        HashMap<String, Object> map = ParamsUtil.getMap();
        map.put("page",page+"");
        map.put("type",type);
        map.put("rows",rows+"");
        ApiManager.getInstence().getApiService().getArticleDetailsList(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<List<Article>>>rxSchedulerHelper())
                .subscribe(new BaseObserver<List<Article>>() {
                    @Override
                    protected void onSuccees(ApiResponse<List<Article>> t) {
                        mMsgView.getArticleListMoreSuccess(t.getResult());
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        mMsgView.getArticleListMoreFail(errorInfo);
                        ToastUtils.showShort(errorInfo);
                    }
                });

    }


//    public void getProjectListLoadMore(int page, int cid){
//        ApiManager.getInstence().getApiService().getProjectListData(page,cid)
//                .compose(RxUtil.<BaseResponse<ProjectListData>>rxSchedulerHelper())
//                .subscribe(new BaseObserver2<ProjectListData>() {
//                    @Override
//                    protected void onSuccees(BaseResponse<ProjectListData> t) {
//                        mMsgView.getArticleListMoreSuccess(t.getData());
//                    }
//
//                    @Override
//                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
//                        mMsgView.getArticleListMoreFail(errorInfo);
//                        ToastUtils.showShort(errorInfo);
//                    }
//                });
//    }

}
