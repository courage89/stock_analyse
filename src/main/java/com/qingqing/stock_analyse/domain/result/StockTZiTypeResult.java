package com.qingqing.stock_analyse.domain.result;


/**
 * 开盘收盘涨停， 最低价低于涨停价
 */
public class StockTZiTypeResult extends BaseAnalyseResult{

	
	//TODO:这个对象的定义有问题？？？
	
	/**
	 * 今日最低价相对昨日最高价的涨幅
	 */
	private Double minIncreasePercent;
	
	/**
	 * 今日最低价
	 */
	private Double minPriceToday;
	
	/**
	 * 今日最高价
	 */
	private Double maxPriceYesterday;

	public Double getMinIncreasePercent() {
		return minIncreasePercent;
	}

	public void setMinIncreasePercent(Double minIncreasePercent) {
		this.minIncreasePercent = minIncreasePercent;
	}

	public Double getMinPriceToday() {
		return minPriceToday;
	}

	public void setMinPriceToday(Double minPriceToday) {
		this.minPriceToday = minPriceToday;
	}

	public Double getMaxPriceYesterday() {
		return maxPriceYesterday;
	}

	public void setMaxPriceYesterday(Double maxPriceYesterday) {
		this.maxPriceYesterday = maxPriceYesterday;
	}
}
