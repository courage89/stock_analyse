package com.qingqing.stock_analyse.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class StockDateUtil {

	public static final Date findLastOpenMarketkDay(Date today){
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		//9 表示最长的黄金周假期+周六、周日
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
		// TODO 待实现
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

	public static void main(String[] args) {
		System.out.println(findLastOpenMarketkDay(new Date()));
	}
}
