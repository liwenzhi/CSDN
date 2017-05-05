package com.example.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;
import com.example.calendar.materialcalendarview.materialcalendarview.CalendarDay;
import com.example.calendar.materialcalendarview.materialcalendarview.CalendarMode;
import com.example.calendar.materialcalendarview.materialcalendarview.MaterialCalendarView;
import com.example.calendar.materialcalendarview.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

public class MainActivity extends Activity {
    MaterialCalendarView materialCalendarView;//布局内的控件
    CalendarDay currentDate;//自定义的日期对象

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);// 實例化

        //编辑日历属性
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)   //设置每周开始的第一天
                .setMinimumDate(CalendarDay.from(2010, 4, 3))  //设置可以显示的最早时间
                .setMaximumDate(CalendarDay.from(2018, 5, 12))//设置可以显示的最晚时间
                .setCalendarDisplayMode(CalendarMode.MONTHS)//设置显示模式，可以显示月的模式，也可以显示周的模式
                .commit();// 返回对象并保存
        //      设置点击日期的监听
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull com.example.calendar.materialcalendarview.materialcalendarview.MaterialCalendarView widget, @NonNull com.example.calendar.materialcalendarview.materialcalendarview.CalendarDay date, boolean selected) {
                currentDate = date;
            }
        });
    }

    /**
     * 获取点击后的日期数
     */
    public void getTime(View view) {
        if (currentDate != null) {
            int year = currentDate.getYear();
            int month = currentDate.getMonth() + 1;   //月份跟系统一样是从0开始的，实际获取时要加1
            int day = currentDate.getDay();
            Toast.makeText(this, currentDate.toString() + "你选中的是：" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "请选择时间", Toast.LENGTH_LONG).show();
        }


    }

}
