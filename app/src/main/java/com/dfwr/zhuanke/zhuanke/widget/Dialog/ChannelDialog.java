package com.dfwr.zhuanke.zhuanke.widget.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dfwr.zhuanke.zhuanke.R;
import com.dfwr.zhuanke.zhuanke.bean.ProjectClassifyData;

import java.util.List;

/**
 * Created by ckx on 2018/7/16.
 */


public class ChannelDialog extends Dialog {

    private Context context;
    List<ProjectClassifyData> mData;

    public ChannelDialog(Context context, final List<ProjectClassifyData> mData) {
        super(context, R.style.RemindDialog);// 必须调用父类的构造函数
        this.context = context;
        this.mData = mData;
        View v = LayoutInflater.from(context).inflate(R.layout.pop_channe, null);
        //关于pop，我们也可以在构造函数中传入view，而不必setContentView，因为构造函数中的view，其实最终也要setContentView
        setContentView(v);
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        Display d = window.getWindowManager().getDefaultDisplay();
        //获取屏幕宽
        wlp.width = (int) (d.getWidth());
        getWindow().setGravity(Gravity.TOP);
        getWindow().setWindowAnimations(R.style.mypopwindow_anim_style);
        window.setAttributes(wlp);
        RecyclerView recyclerViewChannel = v.findViewById(R.id.recyclerView_channel);
        ImageView close = v.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        recyclerViewChannel.setLayoutManager(new GridLayoutManager(context,5));
        Adapter adapter = new Adapter(mData);
        recyclerViewChannel.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                setOnChannelClick.itemOnclick(position);
//                ProjectClassifyData projectClassifyData = mData.get(position);
//                EventBus.getDefault().post(projectClassifyData);
                dismiss();
            }
        });
    }

    private SetOnChannelClickInterFace setOnChannelClick;

    public interface  SetOnChannelClickInterFace{
        void itemOnclick(int position);
    }

    public void SetOnChannelClick(SetOnChannelClickInterFace clickListenerInterface) {
        this.setOnChannelClick = clickListenerInterface;
    }



    class  Adapter extends BaseQuickAdapter<ProjectClassifyData,BaseViewHolder> {

        public Adapter(@Nullable List<ProjectClassifyData> data) {
            super(R.layout.item_channel, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ProjectClassifyData item) {
            helper.setText(R.id.tv_title, item.getType());
        }
    }

}
