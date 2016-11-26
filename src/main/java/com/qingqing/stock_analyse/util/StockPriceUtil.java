package com.qingqing.stock_analyse.util;

public class StockPriceUtil {

	public static final Double getIncreasePercent(Double yesterdayClosePrice, Double todayOpenPrice){
		return (todayOpenPrice - yesterdayClosePrice)/yesterdayClosePrice;
	}
}
