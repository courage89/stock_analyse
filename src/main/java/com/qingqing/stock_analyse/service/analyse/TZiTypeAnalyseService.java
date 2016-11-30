package com.qingqing.stock_analyse.service.analyse;

import java.util.Date;
import java.util.Map;

import com.qingqing.stock_analyse.domain.result.StockTZiTypeResult;

public interface TZiTypeAnalyseService {

	/**
	 * 查询所有指定日期的符合T字型的股票信息
	 */
	Map<String, StockTZiTypeResult> findAllTZiTypeResult(Date date);

	/**
	 * 分析指定日期的所有股票，挑选出所有符合T字型的股票
	 */
	Map<String, StockTZiTypeResult> analyseTZiTypeResult(Date date);

	/**
	 * 分析指定日期，指定代码且符合T字型的股票，查询其涨停策略，若为涨停，则返回null
	 */
	StockTZiTypeResult analyseTZiTypeResult(Date date, String stockCode);
}
