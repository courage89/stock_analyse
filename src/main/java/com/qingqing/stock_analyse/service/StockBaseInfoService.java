package com.qingqing.stock_analyse.service;

import java.util.Date;
import java.util.List;

import com.qingqing.stock_analyse.domain.StockInfo;

public interface StockBaseInfoService {

	/**
	 * ��ѯָ�����ڣ�ָ����Ʊ�Ļ����ǵ����
	 */
	public StockInfo findByStockCodeAndDate(String stockCode, Date date);
	
	/**
	 * ��ѯָ��ʱ����ڣ�ָ����Ʊ�Ļ����ǵ������ ��ʱ������
	 */
	public List<StockInfo> findByStockCodeAndTimeDuration(String stockCode, Date startDate, Date endDate);
}
