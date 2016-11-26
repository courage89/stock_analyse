package com.qingqing.stock_analyse.domain.result;


/**
 * 昨日涨停，今日涨停
 */
public class DoubleIncreaseToCeilResult extends BaseAnalyseResult{

	/**
	 * 昨日涨停价
	 */
	private Double yesterdayCeilPrice;
	
	/**
	 * 涨停价
	 */
	private Double todayCeilPrice;

	public Double getYesterdayCeilPrice() {
		return yesterdayCeilPrice;
	}

	public void setYesterdayCeilPrice(Double yesterdayCeilPrice) {
		this.yesterdayCeilPrice = yesterdayCeilPrice;
	}

	public Double getTodayCeilPrice() {
		return todayCeilPrice;
	}

	public void setTodayCeilPrice(Double todayCeilPrice) {
		this.todayCeilPrice = todayCeilPrice;
	}
}
