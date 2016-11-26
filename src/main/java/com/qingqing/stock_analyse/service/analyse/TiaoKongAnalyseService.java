package com.qingqing.stock_analyse.service.analyse;

import java.util.Date;
import java.util.Map;

import com.qingqing.stock_analyse.domain.result.StockTiaoKongResult;

public interface TiaoKongAnalyseService {

	/**
	 * 查询所有指定日期的跳空股票记录
	 */
	Map<String, StockTiaoKongResult> findAllTiaoKongResult(Date date);
	
	/**
	 * 分析指定日期的所有股票，挑选出所有涨停的股票
	 */
	Map<String, StockTiaoKongResult> analyseTiaoKongResult(Date date);
	
	/**
	 * 分析指定日期，指定代码的股票，查询其涨停策略，若为涨停，则返回null
	 */
	StockTiaoKongResult analyseTiaoKongResult(Date date, String stockCode);
}
