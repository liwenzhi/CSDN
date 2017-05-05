package com.example.calendar.materialcalendarview.materialcalendarview;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Collection;

/**
 * Display a week of {@linkplain com.example.materialcalendarview.materialcalendarview.DayView}s and
 * seven {@linkplain com.example.materialcalendarview.materialcalendarview.WeekDayView}s.
 */
@Experimental
@SuppressLint("ViewConstructor")
public class WeekView extends CalendarPagerView {

    public WeekView(@NonNull MaterialCalendarView view,
                    CalendarDay firstViewDay,
                    int firstDayOfWeek) {
        super(view, firstViewDay, firstDayOfWeek);
    }

    @Override
    protected void buildDayViews(Collection<DayView> dayViews, Calendar calendar) {
        for (int i = 0; i < DEFAULT_DAYS_IN_WEEK; i++) {
            addDayView(dayViews, calendar);
        }
    }

    @Override
    protected boolean isDayEnabled(CalendarDay day) {
        return true;
    }

    @Override
    protected int getRows() {
        return DAY_NAMES_ROW + 1;
    }
}
