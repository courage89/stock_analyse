package com.qingqing.stock_analyse.domain.result;


/**
 * 昨日涨停，今天最低价高于昨天最高价
 */
public class StockTiaoKongResult extends BaseAnalyseResult{

	/**
	 * 今天最低价
	 */
	private Double todayMinPrice;
	
	/**
	 * 昨天涨停价
	 */
	private Double yesterdayCeilPrice;

	public Double getTodayMinPrice() {
		return todayMinPrice;
	}

	public void setTodayMinPrice(Double todayMinPrice) {
		this.todayMinPrice = todayMinPrice;
	}

	public Double getYesterdayCeilPrice() {
		return yesterdayCeilPrice;
	}

	public void setYesterdayCeilPrice(Double yesterdayCeilPrice) {
		this.yesterdayCeilPrice = yesterdayCeilPrice;
	}
}
