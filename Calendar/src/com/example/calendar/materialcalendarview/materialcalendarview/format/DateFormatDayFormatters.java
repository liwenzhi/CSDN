package com.example.calendar.materialcalendarview.materialcalendarview.format;

import android.support.annotation.NonNull;
import com.example.calendar.materialcalendarview.materialcalendarview.CalendarDay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Format using a {@linkplain java.text.DateFormat} instance.
 */
public class DateFormatDayFormatters implements DayFormatters {

    private final DateFormat dateFormat;

    /**
     * Format using a default format
     */
    public DateFormatDayFormatters() {
        this.dateFormat = new SimpleDateFormat("d", Locale.getDefault());
    }

    /**
     * Format using a specific {@linkplain java.text.DateFormat}
     *
     * @param format the format to use
     */
    public DateFormatDayFormatters(@NonNull DateFormat format) {
        this.dateFormat = format;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public String format(@NonNull CalendarDay day) {
        return dateFormat.format(day.getDate());
    }
}
