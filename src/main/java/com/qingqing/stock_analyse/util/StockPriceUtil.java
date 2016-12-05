package com.qingqing.stock_analyse.util;

import com.qingqing.common.util.converter.lang.BigDecimalUtil;

public class StockPriceUtil {

	public static final Double getIncreasePercent(Double yesterdayClosePrice, Double todayOpenPrice) {
		Double incPercent = (todayOpenPrice - yesterdayClosePrice) / yesterdayClosePrice;
		return BigDecimalUtil.convertToBigDecimalWithScale(incPercent, 6).doubleValue();
	}
}
