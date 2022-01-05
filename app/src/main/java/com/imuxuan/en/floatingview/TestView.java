package com.imuxuan.en.floatingview;


import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.imuxuan.floatingview.FloatingMagnetView;
import com.imuxuan.floatingview.MagnetViewListener;

class TestView extends FloatingMagnetView {
    private Activity activity;
    private View rootView;
    private RelativeLayout back,icon;
    private Handler handler;
    private MagnetViewListener listener;
    private Runnable runnable;

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
        listener = new MagnetViewListener() {
            @Override
            public void onRemove(FloatingMagnetView magnetView) {

            }

            @Override
            public void onClick(FloatingMagnetView magnetView) {
                boolean a = isTouchPointInView(icon,getmOriginalRawX(),getmOriginalRawY());
                Log.e("qwer",""+a);
                if (a){
                    if (back.getVisibility() == GONE){
                        back.setVisibility(VISIBLE);
                        handler.removeCallbacks(runnable);
                        handler.postDelayed(runnable,3000);
                    }else {
                        handler.removeCallbacks(runnable);
                        back.setVisibility(GONE);
                    }

                }
            }
        };
        setMagnetViewListener(listener);
        runnable = new Runnable() {
            @Override
            public void run() {
                back.setVisibility(GONE);
            }
        };
    }

    private boolean isTouchPointInView(View view,float x,float y){
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        boolean bX = false;
        if (x >= left && x <= right){
            bX = true;
        }
        boolean bY = false;
        if (y >= top && y <= bottom){
            bY = true;
        }
        if (bX && bY){
            return true;
        }else {
            return false;
        }
    }
}
