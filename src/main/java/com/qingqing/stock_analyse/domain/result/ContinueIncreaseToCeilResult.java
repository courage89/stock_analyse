package com.qingqing.stock_analyse.domain.result;

import java.util.Date;
import java.util.Map;


/**
 * ������ͣ
 */
public class ContinueIncreaseToCeilResult extends BaseAnalyseResult{
	
	/**
	 * ������ͣ�ۣ� ��ʱ��˳���С������
	 */
	private Map<Date, Double> ceilPrices;

	public Map<Date, Double> getCeilPrices() {
		return ceilPrices;
	}

	public void setCeilPrices(Map<Date, Double> ceilPrices) {
		this.ceilPrices = ceilPrices;
	}
}
