package com.qingqing.stock_analyse.domain.result;


/**
 * ������ͣ��������ͼ۸���������߼�
 */
public class StockTiaoKongResult extends BaseAnalyseResult{

	/**
	 * ������ͼ�
	 */
	private Double todayMinPrice;
	
	/**
	 * ������ͣ��
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
