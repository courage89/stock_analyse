package com.qingqing.stock_analyse.domain.result;


/**
 * 脉冲型， 开盘<4%, 最高>9%，收盘<5%
 * (蓝盾股份300297， 2015.3.12)
 */
public class StockPulseResult extends BaseAnalyseResult{

	/**
	 * 开盘价涨幅
	 */
	private Double openIncreasePercent;
	
	/**
	 * 收盘价涨幅
	 */
	private Double closeIncreasePercent;
	
	/**
	 * 最高价涨幅
	 */
	private Double maxIncreasePercent;

	public Double getOpenIncreasePercent() {
		return openIncreasePercent;
	}

	public void setOpenIncreasePercent(Double openIncreasePercent) {
		this.openIncreasePercent = openIncreasePercent;
	}

	public Double getCloseIncreasePercent() {
		return closeIncreasePercent;
	}

	public void setCloseIncreasePercent(Double closeIncreasePercent) {
		this.closeIncreasePercent = closeIncreasePercent;
	}

	public Double getMaxIncreasePercent() {
		return maxIncreasePercent;
	}

	public void setMaxIncreasePercent(Double maxIncreasePercent) {
		this.maxIncreasePercent = maxIncreasePercent;
	}
}
