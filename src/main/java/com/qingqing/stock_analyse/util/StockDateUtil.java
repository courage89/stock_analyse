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

	public static final Date findLastOpenMarketkDay(Date today){
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		for(int i=0; i<9; i++){
			cal.add(Calendar.DAY_OF_MONTH, -1);
			if(Calendar.SATURDAY == cal.get(Calendar.DAY_OF_WEEK) ||
					java.util.Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK) ||
							isHoliday(cal.getTime()) ){
				continue;
			}else{
				return cal.getTime();
			}
		}
		return null;
	}
	
	private static boolean isHoliday(Date time) {
		// TODO
		return false;
	}

	public static final Timestamp getStartTimeOfDate(Date date){
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
		System.out.println(findLastOpenMarketkDay(new Date()));
	}
}
