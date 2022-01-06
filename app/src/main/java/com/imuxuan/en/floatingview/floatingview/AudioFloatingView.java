package com.imuxuan.en.floatingview.floatingview;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.imuxuan.en.floatingview.R;

import spa.lyh.cn.lib_utils.PixelUtils;


public class AudioFloatingView extends FloatingMagnetView {
    private Context context;
    private View rootView;
    private RelativeLayout back,icon;
    private Handler handler;
    private MagnetViewListener listener;
    private Runnable runnable;
    private ImageView audio_close;

    public AudioFloatingView(Context context) {
        this(context,null);
    }

    public AudioFloatingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AudioFloatingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        MARGIN_EDGE = PixelUtils.dip2px(context,15);
        this.context = context;
        initView();
    }

    private void initView(){
        handler = new Handler(Looper.getMainLooper());
        rootView = LayoutInflater.from(context).inflate(R.layout.view_floating, this);
        back = rootView.findViewById(R.id.back);
        icon = rootView.findViewById(R.id.icon);
        audio_close = rootView.findViewById(R.id.audio_close);
        listener = new MagnetViewListener() {
            @Override
            public void onRemove(FloatingMagnetView magnetView) {
                Log.e("qwer","被移除");
            }

            @Override
            public void onClick(FloatingMagnetView magnetView) {
                if (isTouchPointInView(icon,getmOriginalRawX(),getmOriginalRawY())){
                    if (back.getVisibility() == GONE){
                        back.setVisibility(VISIBLE);
                        handler.removeCallbacks(runnable);
                        handler.postDelayed(runnable,3000);
                    }else {
                        handler.removeCallbacks(runnable);
                        back.setVisibility(GONE);
                    }

                }else if (isTouchPointInView(audio_close,getmOriginalRawX(),getmOriginalRawY())){
                    FloatingView.get().remove();
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
