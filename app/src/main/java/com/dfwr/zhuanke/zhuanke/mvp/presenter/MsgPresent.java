package com.dfwr.zhuanke.zhuanke.mvp.presenter;


import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.mvp.contract.IMsgView;

/**
 * Created by wy on 2017/12/5.
 * 消息
 */

public class MsgPresent<T> extends BasePresenter<IMsgView> {
    private IMsgView mMsgView;

    public MsgPresent(IMsgView mMsgView) {
        this.mMsgView = mMsgView;
    }

    @Override
    public void fecth() {

    }













}
