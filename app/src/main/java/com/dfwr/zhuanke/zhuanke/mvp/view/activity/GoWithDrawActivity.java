package com.dfwr.zhuanke.zhuanke.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dfwr.zhuanke.zhuanke.MainActivity;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.base.BaseActivity;
import com.dfwr.zhuanke.zhuanke.base.BasePresenter;
import com.dfwr.zhuanke.zhuanke.widget.Systems;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ckx on 2018/7/17.
 */

public class GoWithDrawActivity extends BaseActivity {



    @BindView(R.id.tv_my_account)
    TextView tvMyAccount;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.tv_go_makemoney)
    TextView tvGoMakemoney;
    @BindView(R.id.activity_guide)
    LinearLayout activityGuide;
    @BindView(R.id.back_btn)
    ImageView backBtn;
    private String[] accountSelects = {"3元(仅限首次)", "10元", "20元", "50元", "100元"};
    private String withdrawType="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_withdraw);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    private void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        final Adapter adaptersex = new Adapter(Arrays.asList(accountSelects));
        recyclerView.setAdapter(adaptersex);
        adaptersex.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                sexPosition = position;
                String account = accountSelects[position];
                adaptersex.notifyDataSetChanged();
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();

        if (intent!=null) {
            withdrawType = "0";
            String payAccount = intent.getStringExtra(Systems.payAccount);
            String payName = intent.getStringExtra(Systems.payName);
        }else{
            withdrawType = "1";
        }



    }


    @OnClick({R.id.tv_submit, R.id.tv_go_makemoney})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                break;
            case R.id.tv_go_makemoney:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(Systems.from_withdraw,"0");
                startActivity(intent);
                break;
        }
    }


    private int sexPosition = 0;

    public class Adapter extends BaseQuickAdapter<String, BaseViewHolder> {
        private int type;

        public Adapter(@Nullable List<String> data) {
            super(R.layout.item_account_select, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            int layoutPosition = helper.getLayoutPosition();
            helper.setText(R.id.tv_title, item);
            if (sexPosition == layoutPosition) {
                helper.setTextColor(R.id.tv_title, getResources().getColor(R.color.bg_white));
                helper.setBackgroundRes(R.id.rl_title, R.drawable.btn_selec);
            } else {
                helper.setTextColor(R.id.tv_title, getResources().getColor(R.color.text_gray_new));
                helper.setBackgroundRes(R.id.rl_title, R.drawable.btn_no_selec_border);
            }
        }
    }


    @Override
    protected BasePresenter createPresent() {
        return new BasePresenter() {
            @Override
            public void fecth() {

            }
        };
    }

}
