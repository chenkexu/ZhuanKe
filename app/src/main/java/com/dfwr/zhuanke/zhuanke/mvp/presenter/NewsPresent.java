package com.dfwr.zhuanke.zhuanke.mvp.presenter;


import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver;
import com.dfwr.zhuanke.zhuanke.api.param.ParamsUtil;
import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.bean.ProjectClassifyData;
import com.dfwr.zhuanke.zhuanke.mvp.contract.NewsView;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;

import java.util.HashMap;
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
        HashMap<String, Object> map = ParamsUtil.getMap();
        ApiManager.getInstence().getApiService().getArticType(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<List<ProjectClassifyData>>>rxSchedulerHelper())
                .subscribe(new BaseObserver<List<ProjectClassifyData>>() {
                    @Override
                    protected void onSuccees(ApiResponse<List<ProjectClassifyData>> t) {
                        if (t!=null) {
                            mMsgView.getProjectClassifyDataSuccess(t.getResult());
                        }
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        ToastUtils.showShort(errorInfo);
                    }
                });
    }






}
