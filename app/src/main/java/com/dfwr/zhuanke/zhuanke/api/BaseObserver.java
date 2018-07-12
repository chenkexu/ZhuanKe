package com.dfwr.zhuanke.zhuanke.api;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.mix.easycalculator.R;
import com.mix.easycalculator.util.UIUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by huang on 2018/4/16.
 */

public  abstract class BaseObserver<T> implements Observer<ApiResponse<T>> {

    protected Context mContext;

    public BaseObserver(Context cxt) {
        this.mContext = cxt;
    }

    public BaseObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(ApiResponse<T> tApiResponse) {
        if (tApiResponse.isSuccess()) {
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
            onFailure(UIUtils.getString(R.string.no_network),true);
        } else {
            onFailure(UIUtils.getString(R.string.no_network),false);
        }
    }

    @Override
    public void onComplete() {
    }


    // 返回成功了,但是code错误
    protected void onCodeError(ApiResponse<T> t){
     //   onFailure("msgText="+t.getMsgText(),false);
        onFailure(t.getMsgText()+"",false);

    }

   //返回成功
    protected abstract void onSuccees(ApiResponse<T> t);

    //返回失败
    protected abstract void onFailure(String errorInfo, boolean isNetWorkError);




}
