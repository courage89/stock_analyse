package com.qingqing.stock_analyse.domain.result;

/**
 * 昨日开盘< 2%，> -2%, 收盘涨9%以上， 今日最高价低于昨日收盘价， 最低价高于昨日收盘价-5%以内 .
 * （四环生物000518， 2014.10.10）（中国重工， 2015.4.15）
 */
public class StockPluseSclientResult {
	
	/**
	 * 昨日开盘价涨幅
	 */
	private Double yesterdayOpenIncreasePercent;
	
	/**
	 * 昨日收盘涨幅
	 */
	private Double yesterdayCloseIncreasePercent;
	
	/**
	 * 今日最高价涨幅
	 */
	private Double todayMaxIncreasePercent;
	
	/**
	 * 今日最低价涨幅
	 */
	private Double todayMinIncreasePercent;

	public Double getYesterdayOpenIncreasePercent() {
		return yesterdayOpenIncreasePercent;
	}

	public void setYesterdayOpenIncreasePercent(Double yesterdayOpenIncreasePercent) {
		this.yesterdayOpenIncreasePercent = yesterdayOpenIncreasePercent;
	}

	public Double getYesterdayCloseIncreasePercent() {
		return yesterdayCloseIncreasePercent;
	}

	public void setYesterdayCloseIncreasePercent(Double yesterdayCloseIncreasePercent) {
		this.yesterdayCloseIncreasePercent = yesterdayCloseIncreasePercent;
	}

	public Double getTodayMaxIncreasePercent() {
		return todayMaxIncreasePercent;
	}

	public void setTodayMaxIncreasePercent(Double todayMaxIncreasePercent) {
		this.todayMaxIncreasePercent = todayMaxIncreasePercent;
	}

	public Double getTodayMinIncreasePercent() {
		return todayMinIncreasePercent;
	}

	public void setTodayMinIncreasePercent(Double todayMinIncreasePercent) {
		this.todayMinIncreasePercent = todayMinIncreasePercent;
	}
}
