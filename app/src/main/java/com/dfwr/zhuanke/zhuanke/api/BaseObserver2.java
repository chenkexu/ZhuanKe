package com.dfwr.zhuanke.zhuanke.api;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.dfwr.zhuanke.zhuanke.api.response.BaseResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author quchao
 * @date 2017/11/27
 *
 * @param <T>
 */

public abstract class BaseObserver2<T> implements Observer<BaseResponse<T>> {

    protected Context mContext;

    public BaseObserver2(Context context) {
        this.mContext = context;
    }

    public BaseObserver2() {
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(BaseResponse<T> tApiResponse) {
        if (tApiResponse.getErrorCode() == 0) {
            onSuccees(tApiResponse);
        } else {
            onCodeError(tApiResponse);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ConnectException
                || e instanceof TimeoutException
                || e instanceof NetworkErrorException
                || e instanceof UnknownHostException
                || e instanceof SocketTimeoutException) {
            onFailure("网络异常",true);
        } else {
            onFailure("网络异常",false);
        }
    }

    @Override
    public void onComplete() {
    }


    // 返回成功了,但是code错误
    protected void onCodeError(BaseResponse<T> t){
        onFailure(t.getErrorMsg()+"",false);

    }

    //返回成功
    protected abstract void onSuccees(BaseResponse<T> t);

    //返回失败
    protected abstract void onFailure(String errorInfo, boolean isNetWorkError);
}
