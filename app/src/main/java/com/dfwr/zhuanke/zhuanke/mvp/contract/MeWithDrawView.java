package com.dfwr.zhuanke.zhuanke.mvp.contract;


import com.dfwr.zhuanke.zhuanke.base.BaseView;
import com.dfwr.zhuanke.zhuanke.bean.CheckWithDrawBean;

/**
 * Created by wy on 2017/12/10.
 */

public interface MeWithDrawView extends BaseView {

    void getCheckWithDrawSuccess(CheckWithDrawBean object);

    void withdrawSuccess(Object object);

}
