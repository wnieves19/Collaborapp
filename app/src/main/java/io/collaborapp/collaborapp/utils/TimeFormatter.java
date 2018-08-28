package io.collaborapp.collaborapp.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Luis Padro 7/12/2016.
 */
public class TimeFormatter {
    public static final int TODAY = 0;
    public static final int YESTERDAY = 1;
    public static final int OLDER = 2;

    public TimeFormatter() {
    }

    public String formatTime(Long time) {

        SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
        df.setTimeZone(TimeZone.getDefault());

        return df.format(time);
    }

    public String formatDate(Long time) {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        df.setTimeZone(TimeZone.getDefault());

        return df.format(time);
    }

    public String formatLongDate(Long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        int date = c.get(Calendar.DATE);
        int year = c.get(Calendar.YEAR);
        SimpleDateFormat monthDate = new SimpleDateFormat("MMMM");
        String monthName = monthDate.format(c.getTime());

        return monthName + " " + date + ", " + year;
    }

    public boolean isDateDifferent(Long firstTimeStamp, Long secondTimeStamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(firstTimeStamp);
        int date = c.get(Calendar.DATE);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(secondTimeStamp);
        int date2 = c2.get(Calendar.DATE);
        int month2 = c2.get(Calendar.MONTH);
        int year2 = c2.get(Calendar.YEAR);


        if (date != date2 || month != month2 || year != year2) {
            return true;
        }
        return false;

    }

    public int when(Long time) {
        Calendar c = Calendar.getInstance();
        int date = c.get(Calendar.DATE);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        c.setTimeInMillis(time);
        if (c.get(Calendar.DATE) == date && c.get(Calendar.MONTH) == month && c.get(Calendar.YEAR) == year) {
            return TODAY;
        } else if (c.get(Calendar.DATE) == date - 1 && c.get(Calendar.MONTH) == month && c.get(Calendar.YEAR) == year) {
            return YESTERDAY;
        }
        return OLDER;

    }

}
