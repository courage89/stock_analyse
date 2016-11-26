package com.qingqing.stock_analyse.domain.result;


/**
 * �����ͣ� ����<4%, ���>9%������<5%
 * (���ܹɷ�300297�� 2015.3.12)
 */
public class StockPulseResult extends BaseAnalyseResult{

	/**
	 * ���̼��Ƿ�
	 */
	private Double openIncreasePercent;
	
	/**
	 * ���̼��Ƿ�
	 */
	private Double closeIncreasePercent;
	
	/**
	 * ��߼��Ƿ�
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
