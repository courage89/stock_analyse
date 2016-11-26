package com.qingqing.stock_analyse.domain.result;


/**
 * ����������ͣ�� ��ͼ۵�����ͣ��
 */
public class StockTZiTypeResult extends BaseAnalyseResult{

	/**
	 * ������ͼ�
	 */
	private Double currentMin;
	
	/**
	 * ������߼�
	 */
	private Double currentMax;

	/**
	 * ������߼�
	 */
	private Double prevMax;

	/**
	 * ������ͼ����������߼۵��Ƿ�
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
