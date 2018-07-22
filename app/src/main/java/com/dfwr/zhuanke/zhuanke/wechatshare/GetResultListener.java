package com.dfwr.zhuanke.zhuanke.wechatshare;

public interface GetResultListener<T> {
    void onError();

    void onSuccess(T t);
}