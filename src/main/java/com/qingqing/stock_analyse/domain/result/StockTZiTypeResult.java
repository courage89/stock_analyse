package com.qingqing.stock_analyse.domain.result;


/**
 * ����������ͣ�� ��ͼ۵�����ͣ��
 */
public class StockTZiTypeResult extends BaseAnalyseResult{

	
	//TODO:�������Ķ��������⣿����
	
	/**
	 * ������ͼ����������߼۵��Ƿ�
	 */
	private Double minIncreasePercent;
	
	/**
	 * ������ͼ�
	 */
	private Double minPriceToday;
	
	/**
	 * ������߼�
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
