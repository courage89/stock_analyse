package com.qingqing.stock_analyse.service;

import java.util.Date;
import java.util.List;

import com.qingqing.stock_analyse.domain.StockBaseInfo;

public interface StockBaseInfoService {

	/**
	 * ��ѯָ�����ڣ�ָ����Ʊ�Ļ����ǵ����
	 */
	public StockBaseInfo findByStockCodeAndDate(String stockCode, Date date);
	
	/**
	 * ��ѯָ��ʱ����ڣ�ָ����Ʊ�Ļ����ǵ������ ��ʱ������
	 */
	public List<StockBaseInfo> findByStockCodeAndTimeDuration(String stockCode, Date startDate, Date endDate);
}
