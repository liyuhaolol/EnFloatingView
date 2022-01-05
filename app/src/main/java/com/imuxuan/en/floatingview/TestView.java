package com.imuxuan.en.floatingview;


import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.imuxuan.floatingview.FloatingMagnetView;

class TestView extends FloatingMagnetView {
    private Activity activity;
    private View rootView;
    private RelativeLayout back,icon;
    private Handler handler;

    public TestView(Activity context) {
        this(context,null);
    }

    public TestView(Activity context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView(Activity context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.activity = context;
        initView();
    }

    private void initView(){
        handler = new Handler(Looper.getMainLooper());
        rootView = LayoutInflater.from(activity).inflate(R.layout.view_floating, this);
        back = rootView.findViewById(R.id.back);
        icon = rootView.findViewById(R.id.icon);
        icon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                back.setVisibility(VISIBLE);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        back.setVisibility(GONE);
                    }
                },3000);
            }
        });
    }
}
