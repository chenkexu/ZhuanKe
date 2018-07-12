package com.dfwr.zhuanke.zhuanke.api;

import android.text.TextUtils;

import com.dfwr.zhuanke.zhuanke.mvp.contract.AbstractView;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * @author quchao
 * @date 2017/11/27
 *
 * @param <T>
 */

public abstract class BaseObserver2<T> extends ResourceObserver<T> {

    private AbstractView mView;
    private String mErrorMsg;
    private boolean isShowError = true;

    protected BaseObserver2(AbstractView view){
        this.mView = view;
    }

    protected BaseObserver2(AbstractView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver2(AbstractView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseObserver2(AbstractView view, String errorMsg, boolean isShowError){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof ServerException) {
            mView.showErrorMsg(e.toString());
        } else if (e instanceof HttpException) {
                mView.showErrorMsg("网络异常");
        } else {
            mView.showErrorMsg("网络异常");
        }
        if (isShowError) {
            mView.showError();
        }
    }
}
