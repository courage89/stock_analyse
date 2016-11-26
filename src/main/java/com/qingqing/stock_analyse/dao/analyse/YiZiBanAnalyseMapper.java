package com.qingqing.stock_analyse.dao.analyse;

import java.util.Date;
import java.util.List;

import com.qingqing.stock_analyse.domain.result.StockYiZiBanResult;

public interface YiZiBanAnalyseMapper {

	void insertIngore(StockYiZiBanResult result);

	List<StockYiZiBanResult> findAllByDate(Date date);

}
