package com.qingqing.stock_analyse.domain.result;

/**
 * ���տ���< 2%��> -2%, ������9%���ϣ� ������߼۵����������̼ۣ� ��ͼ۸����������̼�-5%���� .
 * ���Ļ�����000518�� 2014.10.10�����й��ع��� 2015.4.15��
 */
public class StockPluseSclientResult {
	
	/**
	 * ���տ��̼��Ƿ�
	 */
	private Double yesterdayOpenIncreasePercent;
	
	/**
	 * ���������Ƿ�
	 */
	private Double yesterdayCloseIncreasePercent;
	
	/**
	 * ������߼��Ƿ�
	 */
	private Double todayMaxIncreasePercent;
	
	/**
	 * ������ͼ��Ƿ�
	 */
	private Double todayMinIncreasePercent;

	public Double getYesterdayOpenIncreasePercent() {
		return yesterdayOpenIncreasePercent;
	}

	public void setYesterdayOpenIncreasePercent(Double yesterdayOpenIncreasePercent) {
		this.yesterdayOpenIncreasePercent = yesterdayOpenIncreasePercent;
	}

	public Double getYesterdayCloseIncreasePercent() {
		return yesterdayCloseIncreasePercent;
	}

	public void setYesterdayCloseIncreasePercent(Double yesterdayCloseIncreasePercent) {
		this.yesterdayCloseIncreasePercent = yesterdayCloseIncreasePercent;
	}

	public Double getTodayMaxIncreasePercent() {
		return todayMaxIncreasePercent;
	}

	public void setTodayMaxIncreasePercent(Double todayMaxIncreasePercent) {
		this.todayMaxIncreasePercent = todayMaxIncreasePercent;
	}

	public Double getTodayMinIncreasePercent() {
		return todayMinIncreasePercent;
	}

	public void setTodayMinIncreasePercent(Double todayMinIncreasePercent) {
		this.todayMinIncreasePercent = todayMinIncreasePercent;
	}
}
