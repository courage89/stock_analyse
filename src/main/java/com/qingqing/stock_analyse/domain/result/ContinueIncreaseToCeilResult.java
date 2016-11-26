package com.qingqing.stock_analyse.domain.result;

import java.util.Date;
import java.util.Map;


/**
 * 连续涨停
 */
public class ContinueIncreaseToCeilResult extends BaseAnalyseResult{
	
	/**
	 * 连续涨停价， 按时间顺序从小往大拍
	 */
	private Map<Date, Double> ceilPrices;

	public Map<Date, Double> getCeilPrices() {
		return ceilPrices;
	}

	public void setCeilPrices(Map<Date, Double> ceilPrices) {
		this.ceilPrices = ceilPrices;
	}
}
