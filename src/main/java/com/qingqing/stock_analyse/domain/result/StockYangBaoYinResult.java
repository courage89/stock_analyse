package com.qingqing.stock_analyse.domain.result;


/**
 * �����5%���ϣ� �������̼۸������쿪�̼�
 * ���������� �Ͻ��ҵ601899�� 2015.9.16��
 */
public class StockYangBaoYinResult extends BaseAnalyseResult{

	/**
	 * �����Ƿ�
	 */
	private Double yesterdayIncreasePercent;
	
	/**
	 * �����Ƿ�
	 */
	private Double todayIncreasePercent;
	
	/**
	 * �������̼���������տ��̼۵��Ƿ�
	 */
	private Double startEndIncreasePercent;

	public Double getYesterdayIncreasePercent() {
		return yesterdayIncreasePercent;
	}

	public void setYesterdayIncreasePercent(Double yesterdayIncreasePercent) {
		this.yesterdayIncreasePercent = yesterdayIncreasePercent;
	}

	public Double getTodayIncreasePercent() {
		return todayIncreasePercent;
	}

	public void setTodayIncreasePercent(Double todayIncreasePercent) {
		this.todayIncreasePercent = todayIncreasePercent;
	}

	public Double getStartEndIncreasePercent() {
		return startEndIncreasePercent;
	}

	public void setStartEndIncreasePercent(Double startEndIncreasePercent) {
		this.startEndIncreasePercent = startEndIncreasePercent;
	}
}
