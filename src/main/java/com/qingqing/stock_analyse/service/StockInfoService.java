package com.qingqing.stock_analyse.service;

import java.util.Date;
import java.util.List;

import com.qingqing.stock_analyse.domain.StockInfo;

public interface StockInfoService {

	/**
	 * 查询指定日期，指定股票的基本涨跌情况
	 */
	public StockInfo findByStockCodeAndDate(String stockCode, Date date);

	/**
	 * 查询指定时间段内，指定股票的基本涨跌情况， 按时间排序
	 */
	public List<StockInfo> findByStockCodeAndTimeDuration(String stockCode, Date startDate, Date endDate);

	public void insertStockInfo(List<StockInfo> stockInfos);
}
