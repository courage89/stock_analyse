package com.qingqing.stock_analyse.domain.result;


/**
 * ������ͣ��������ͼ۸���������߼�
 */
public class StockTiaoKongResult extends BaseAnalyseResult{

	/**
	 * ������ͼ�
	 */
	private Double currentMin;
	
	/**
	 * ������ͣ��
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
