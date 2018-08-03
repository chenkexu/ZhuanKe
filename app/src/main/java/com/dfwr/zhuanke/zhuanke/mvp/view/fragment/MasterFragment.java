package com.dfwr.zhuanke.zhuanke.mvp.view.fragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.adapter.HomeAdapter;
import com.dfwr.zhuanke.zhuanke.base.BaseTwoFragment;
import com.dfwr.zhuanke.zhuanke.bean.HomeBean;
import com.dfwr.zhuanke.zhuanke.bean.Propertie;
import com.dfwr.zhuanke.zhuanke.bean.UserBaseInfo;
import com.dfwr.zhuanke.zhuanke.mvp.contract.IHomeView;
import com.dfwr.zhuanke.zhuanke.mvp.presenter.HomePresent;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.LoginActivity;
import com.dfwr.zhuanke.zhuanke.mvp.view.activity.MyCodeActivity;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesTool;
import com.dfwr.zhuanke.zhuanke.util.SharedPreferencesUtil;
import com.dfwr.zhuanke.zhuanke.widget.Dialog.ShareDialog;
import com.dfwr.zhuanke.zhuanke.widget.Systems;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ckx on 2018/7/12.
 */
public class MasterFragment extends BaseTwoFragment<IHomeView,HomePresent<IHomeView>> implements IHomeView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.tv_today_account)
    TextView tvTodayStudentNum;
    @BindView(R.id.tv_all_pupil)
    TextView tvAllPupil;
    @BindView(R.id.tv_today_pupil)
    TextView tvTodayStudentProfit;
    @BindView(R.id.tv_all__pupil_account)
    TextView tvAllStudentPofit;
    @BindView(R.id.tv_master_reward)
    TextView tvMasterReWard;
    @BindView(R.id.tv_master_tips)
    TextView tvMasterTipsStr;
    private  String imageUrl = "https://raw.githubusercontent.com/yipianfengye/android-adDialog/master/images/testImage2.png";

    private List<HomeBean> imagesAndTitles = new ArrayList<>();
    private HomeAdapter taskAdapter;


    private int[] taskStatusPics = {R.mipmap.wechat_withdraw, R.mipmap.qq_withdraw,
            R.mipmap.code_withdraw, R.mipmap.link_withdraw
    };

    private String[] myStr = {"微信收徒", "QQ收徒", "二维码收徒", "链接收徒"};
    private Propertie propertie;
    private String studentLink;
    private ShareDialog shareDialog;





    @Override
    protected int setLayoutId() {
        return R.layout.fragment_master;
    }


    @Override
    protected void fragmentToUserVisible() {
        super.fragmentToUserVisible();
        getUserData();
    }



    public void getUserData() {
        Bundle arguments = getArguments();
        propertie = (Propertie) arguments.getSerializable(Systems.propertie);
        mPresent.getUserInfo();
    }


    @Override
    protected void initData() {
        super.initData();
        tvAllStudentPofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                getUserData();
            }
        });

        for (int i = 0; i < taskStatusPics.length; i++) {
            HomeBean homeBean = new HomeBean(myStr[i], taskStatusPics[i]);
            imagesAndTitles.add(homeBean);
        }
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        taskAdapter = new HomeAdapter(imagesAndTitles);
        recyclerView.setAdapter(taskAdapter);
        getUserData();
        mPresent.getStudentLink();


        taskAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                String studentLink = SharedPreferencesUtil.getStringData(getActivity(), SharedPreferencesUtil.student_link);

                shareDialog = new ShareDialog(getActivity(),studentLink);
                switch (position){
                    case 0:
                        shareDialog.setWeChat();
                        shareDialog.show();
                        break;
                    case 1:
                        shareDialog.setQQ();
                        shareDialog.show();
                        break;
                    case 2:
                        Intent intent = new Intent(getActivity(), MyCodeActivity.class);
                        intent.putExtra(Systems.propertie,propertie);
                        startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_right_in, R.anim.slide_right_out);
                        break;
                    case 3:
                        ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                        // 将文本内容放到系统剪贴板里。
                        cm.setText(studentLink);
                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                        alertDialog.setMessage("徒弟链接已复制，快打开浏览器访问吧!")
                                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).create().show();
//                        ToastUtils.showLong("徒弟链接已复制，快打开浏览器访问吧");
                        break;
                }
            }
        });

        initPrice();
    }

    private void initPrice() {
        if (propertie!=null) {
            String masterPrice = propertie.getStudent_reward_n_to_teacher();
            String strIntegration = getResources().getString(R.string.master_reward_tips, masterPrice);
            tvMasterReWard.setText(Html.fromHtml(strIntegration));


            String student_get_balance_to_teacher = propertie.getStudent_get_balance_to_teacher();
            String register_reward = propertie.getRegister_reward();
            String student_reward_to_teacher = propertie.getStudent_reward_to_teacher();

            int masterwithDrawProfit = Integer.parseInt(student_get_balance_to_teacher) + Integer.parseInt(register_reward) + Integer.parseInt(student_reward_to_teacher);

            String pricePercent = "30%";

            String string = getResources().getString(R.string.master_profit_tips, masterwithDrawProfit, pricePercent);
            tvMasterTipsStr.setText(Html.fromHtml(string));
        }
    }


    @Override
    protected HomePresent<IHomeView> createPresent() {
        return new HomePresent<>(this);
    }


    @Override
    public void showLoading() {
        showDefaultLoading();
    }

    @Override
    public void hideLoading() {
        hideDefaultLoading();
    }



    @Override
    public void getUserInfo(UserBaseInfo userBaseInfo) {
        tvAllPupil.setText(userBaseInfo.getStudentNum()+"");
        tvTodayStudentNum.setText(userBaseInfo.getTodayStudentNum()+"");
        tvTodayStudentProfit.setText(userBaseInfo.getTodayStudentPofit()+"");
        tvAllStudentPofit.setText(userBaseInfo.getStudentPofit()+"");
        SharedPreferencesUtil.putStringData(getActivity(), SharedPreferencesTool.balance,userBaseInfo.getAccount().getBalance()+"");
    }


    @Override
    public void getStudentLink(String studentLink) {
        Logger.d("接收到的studentLink是："+studentLink);
        SharedPreferencesUtil.putStringData(getActivity(),SharedPreferencesUtil.student_link,studentLink);
    }


}
