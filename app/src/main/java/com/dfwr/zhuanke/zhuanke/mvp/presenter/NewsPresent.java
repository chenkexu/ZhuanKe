package com.dfwr.zhuanke.zhuanke.mvp.presenter;


import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.mvp.contract.NewsView;

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
        
    }











}
