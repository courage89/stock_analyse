package com.qingqing.stock_analyse.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class StockDateUtil {

    public static final String DATE_TO_YEAR_MONTH_DAY = "yyyy-MM-dd";

    public static final Date getDate(int year, int month, int day) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public static final Date findLastestOpenMarketkDay(Date today) {
        return findLastestOpenMarketkDay(today, true);
    }

    public static final Date findLastestOpenMarketkDayExceptToday(Date today) {
        return findLastestOpenMarketkDay(today, false);
    }

    private static final Date findLastestOpenMarketkDay(Date today, boolean includeToday) {
        Calendar cal = getOneDayStartTimeInCalendar(today);
        if (includeToday && isOpenMarketDay(cal)) {
            return cal.getTime();
        }

        for (int i = 0; i < 9; i++) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
            if (isOpenMarketDay(cal)) {
                return cal.getTime();
            }
        }
        return null;
    }

    private static final Calendar getOneDayStartTimeInCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    private static final boolean isOpenMarketDay(Calendar cal) {
        return (Calendar.SATURDAY != cal.get(Calendar.DAY_OF_WEEK)) &&
                (java.util.Calendar.SUNDAY != cal.get(Calendar.DAY_OF_WEEK)) &&
                !isHoliday(cal.getTime());
    }

    private static boolean isHoliday(Date time) {
        // TODO
        return false;
    }

    public static final Timestamp getStartTimeOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }


    public static Date stringToDate(String dateStr) {
        Date date = null;
        SimpleDateFormat shortDateFormat = new SimpleDateFormat(DATE_TO_YEAR_MONTH_DAY);
        try {
            date = shortDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TO_YEAR_MONTH_DAY);
        if (date == null) {
            return "";
        }
        return sdf.format(date);
    }

    public static void main(String[] args) {
        Date day1 = findLastestOpenMarketkDay(new Date());
        Date day2 = findLastestOpenMarketkDay(day1);
        System.out.println(day1);
        System.out.println(day2);
    }
}
