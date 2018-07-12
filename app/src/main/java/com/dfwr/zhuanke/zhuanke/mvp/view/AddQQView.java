package com.dfwr.zhuanke.zhuanke.mvp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.dfwr.zhuanke.zhuanke.R;

import butterknife.ButterKnife;

/**
 * Created by ckx on 2018/6/21.
 */

public class AddQQView extends FrameLayout {

    private Context context;

    public AddQQView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public AddQQView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View view = LayoutInflater.from(context)
                .inflate(R.layout.include_add_qq, this);
        ButterKnife.bind(view);
    }
}
