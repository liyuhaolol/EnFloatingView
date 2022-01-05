package com.imuxuan.en.floatingview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.imuxuan.floatingview.FloatingMagnetView;
import com.imuxuan.floatingview.FloatingView;
import com.imuxuan.floatingview.MagnetViewListener;

/**
 * @ClassName TestActivity
 * @Description
 * @Author Yunpeng Li
 * @Creation 2018/3/15 下午5:01
 * @Mender Yunpeng Li
 * @Modification 2018/3/15 下午5:01
 */
public class TestActivity extends BaseActivity {
    static int pageNum = 1;
    TestView testView;
    TextView page_num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        page_num =  findViewById(R.id.page_num);
        page_num.setText("页面" + pageNum);
        page_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("qwer","1234");
                FloatingView.get().customView(testView);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.BOTTOM | Gravity.START;
                params.setMargins(13, params.topMargin, params.rightMargin, dip2px(TestActivity.this,100));
                FloatingView.get().layoutParams(params);
                FloatingView.get().add();
                FloatingView.get().listener(new MagnetViewListener() {
                    @Override
                    public void onRemove(FloatingMagnetView magnetView) {
                        Toast.makeText(TestActivity.this, "我没了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onClick(FloatingMagnetView magnetView) {
                        Toast.makeText(TestActivity.this, "点到我了", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        testView = new TestView(this);

        /*Dialog dialog = new Dialog(this);
        dialog.show();*/
    }

    public void createActivity(View view) {
        pageNum++;
        Intent intent = new Intent();
        intent.setClass(this, TestActivity.class);
        startActivity(intent);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale);
    }
}
