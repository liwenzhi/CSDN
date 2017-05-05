package com.example.SwipeBack;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import com.example.SwipeBack.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 右滑关闭页面效果
 */
public class MyActivity extends SwipeBackActivity   {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        textView.setBackgroundColor(Color.RED);
        textView.setText("MainActivity页面");
        setContentView(textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this, SecondActivity.class));
            }
        });

        //设置不可滑动关闭
        setSwipeBackEnable(false);

    }



}
