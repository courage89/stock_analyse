package com.qingqing.stock_analyse.domain.result;


/**
 * 开盘收盘涨停， 最低价低于涨停价
 */
public class StockTZiTypeResult extends BaseAnalyseResult{

	/**
	 * 今日最低价
	 */
	private Double currentMin;

	/**
	 * 今日最高价
	 */
	private Double currentMax;

	/**
	 * 昨日最低价
	 */
	private Double prevMax;

	/**
	 * 今日最低价相对昨日最高价的涨幅
	 */
	private Double minIncrPercent;

	public Double getCurrentMin() {
		return currentMin;
	}

	public void setCurrentMin(Double currentMin) {
		this.currentMin = currentMin;
	}

	public Double getCurrentMax() {
		return currentMax;
	}

	public void setCurrentMax(Double currentMax) {
		this.currentMax = currentMax;
	}

	public Double getPrevMax() {
		return prevMax;
	}

	public void setPrevMax(Double prevMax) {
		this.prevMax = prevMax;
	}

	public Double getMinIncrPercent() {
		return minIncrPercent;
	}

	public void setMinIncrPercent(Double minIncrPercent) {
		this.minIncrPercent = minIncrPercent;
	}
}
