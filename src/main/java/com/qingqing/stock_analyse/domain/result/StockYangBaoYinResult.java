package com.qingqing.stock_analyse.domain.result;


/**
 * 昨天跌5%以上， 今天收盘价高于昨天开盘价
 * （阳包阴， 紫金矿业601899， 2015.9.16）
 */
public class StockYangBaoYinResult extends BaseAnalyseResult{

	/**
	 * 昨日涨幅
	 */
	private Double yesterdayIncreasePercent;
	
	/**
	 * 今日涨幅
	 */
	private Double todayIncreasePercent;
	
	/**
	 * 今日收盘价相对于昨日开盘价的涨幅
	 */
	private Double startEndIncreasePercent;

	public Double getYesterdayIncreasePercent() {
		return yesterdayIncreasePercent;
	}

	public void setYesterdayIncreasePercent(Double yesterdayIncreasePercent) {
		this.yesterdayIncreasePercent = yesterdayIncreasePercent;
	}

	public Double getTodayIncreasePercent() {
		return todayIncreasePercent;
	}

	public void setTodayIncreasePercent(Double todayIncreasePercent) {
		this.todayIncreasePercent = todayIncreasePercent;
	}

	public Double getStartEndIncreasePercent() {
		return startEndIncreasePercent;
	}

	public void setStartEndIncreasePercent(Double startEndIncreasePercent) {
		this.startEndIncreasePercent = startEndIncreasePercent;
	}
}
