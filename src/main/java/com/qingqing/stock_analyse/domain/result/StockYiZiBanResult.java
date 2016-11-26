package com.qingqing.stock_analyse.domain.result;


public class StockYiZiBanResult extends BaseAnalyseResult {

	/**
	 * 一字板的价格
	 */
	private Double price;
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
