package com.qingqing.stock_analyse.domain.result;


/**
 * ������ͣ��������ͣ
 */
public class DoubleIncreaseToCeilResult extends BaseAnalyseResult{

	/**
	 * ������ͣ��
	 */
	private Double yesterdayCeilPrice;
	
	/**
	 * ��ͣ��
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
