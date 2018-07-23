package com.dfwr.zhuanke.zhuanke.mvp.presenter;


import android.os.CountDownTimer;

import com.blankj.utilcode.util.ToastUtils;
import com.dfwr.zhuanke.zhuanke.api.ApiManager;
import com.dfwr.zhuanke.zhuanke.api.BaseObserver;
import com.dfwr.zhuanke.zhuanke.api.param.ParamsUtil;
import com.dfwr.zhuanke.zhuanke.api.response.ApiResponse;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.mvp.contract.MeWithDrawView;
import com.dfwr.zhuanke.zhuanke.mvp.event.UpdateSmsStateEvent;
import com.dfwr.zhuanke.zhuanke.util.RxUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * Created by wy on 2017/12/5.
 * 提现
 */

public class MeWithDrawPresent<T> extends BasePresenter<MeWithDrawView> {

    private MeWithDrawView rankView;

    public MeWithDrawPresent(MeWithDrawView mMsgView) {
        this.rankView = mMsgView;
    }

    @Override
    public void fecth() {

    }


    private String codeId = "";
    public void sendMessageCode(String phone){
        rankView.showLoading();
        HashMap<String, Object> map = ParamsUtil.getMap();
        map.put("phone", phone);
        ApiManager.getInstence().getApiService().sendMessage(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<Object>>rxSchedulerHelper())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    protected void onSuccees(ApiResponse<Object> t) {
                        rankView.hideLoading();
                        countDown();
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        rankView.hideLoading();
                        ToastUtils.showShort(errorInfo);

                    }
                });
    }


    //倒计时开始
    private long totalTime = 60000;
    private long progress = 0;
    private CountDownTimer timer;
    private void countDown() {
        progress = totalTime;
        timer = new CountDownTimer(totalTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //不可再次获取验证码
                progress -= 1000;
                String currentTime = progress / 1000 + "s";
                UpdateSmsStateEvent updateSmsTimeEvent = new UpdateSmsStateEvent();
                updateSmsTimeEvent.isCall = false;
                updateSmsTimeEvent.surplusTime = currentTime;
                EventBus.getDefault().post(updateSmsTimeEvent);
            }

            @Override
            public void onFinish() {
                //可以再次获取验证码
                progress = totalTime;
                UpdateSmsStateEvent updateSmsTimeEvent = new UpdateSmsStateEvent();
                updateSmsTimeEvent.isCall = true;
                updateSmsTimeEvent.surplusTime = "0s";
                EventBus.getDefault().post(updateSmsTimeEvent);
            }
        };
        timer.start();
    }






    public void weChatTakeMoney(String money){
        rankView.showLoading();
        HashMap<String, Object> map = ParamsUtil.getMap();
        map.put("money", money);
        rankView.showLoading();
        ApiManager.getInstence().getApiService().weChatTakeMoney(ParamsUtil.getParams(map))
                .compose(RxUtil.<ApiResponse<Object>>rxSchedulerHelper())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    protected void onSuccees(ApiResponse<Object> t) {

                        rankView.hideLoading();
                    }

                    @Override
                    protected void onFailure(String errorInfo, boolean isNetWorkError) {
                        rankView.hideLoading();
                        ToastUtils.showShort(errorInfo);


                    }
                });
    }


}
