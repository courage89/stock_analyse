package com.qingqing.stock_analyse.domain.result;


/**
 * 昨日涨停，今天最低价高于昨天最高价
 */
public class StockTiaoKongResult extends BaseAnalyseResult{

	/**
	 * 今天最低价
	 */
	private Double currentMin;

	/**
	 * 昨天涨停价
	 */
	private Double prevMax;

	public Double getCurrentMin() {
		return currentMin;
	}

	public void setCurrentMin(Double currentMin) {
		this.currentMin = currentMin;
	}

	public Double getPrevMax() {
		return prevMax;
	}

	public void setPrevMax(Double prevMax) {
		this.prevMax = prevMax;
	}
}
