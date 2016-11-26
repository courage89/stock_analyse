package com.qingqing.stock_analyse.dao.analyse;

import java.util.Date;
import java.util.List;

import com.qingqing.stock_analyse.domain.result.StockPulseResult;

public interface PulseAnalyseMapper {

	List<StockPulseResult> findAllByDate(Date date);

	void insertIgnore(StockPulseResult result);

}
