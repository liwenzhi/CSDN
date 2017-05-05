package com.example.calendar.materialcalendarview.materialcalendarview.format;

import android.support.annotation.NonNull;
import com.example.calendar.materialcalendarview.materialcalendarview.CalendarDay;


/**
 * Supply labels for a given day. Default implementation is to format using a {@linkplain java.text.SimpleDateFormat}
 */
public interface DayFormatters {

    /**
     * Format a given day into a string
     *
     * @param day the day
     * @return a label for the day
     */
    @NonNull
    String format(@NonNull CalendarDay day);

    /**
     */
    public static final DayFormatters DEFAULT = new DateFormatDayFormatters();

}
