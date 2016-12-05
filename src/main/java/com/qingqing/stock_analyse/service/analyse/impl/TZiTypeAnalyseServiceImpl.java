package com.qingqing.stock_analyse.service.analyse.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qingqing.common.util.converter.lang.DoubleCompareUtil;
import com.qingqing.stock_analyse.constants.StockAnalyseConstants;
import com.qingqing.stock_analyse.dao.analyse.StockTZiTypeResultMapper;
import com.qingqing.stock_analyse.domain.StockInfo;
import com.qingqing.stock_analyse.domain.result.StockTiaoKongResult;
import com.qingqing.stock_analyse.util.StockDateUtil;
import com.qingqing.stock_analyse.util.StockPriceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qingqing.stock_analyse.domain.result.StockTZiTypeResult;
import com.qingqing.stock_analyse.service.StockInfoService;
import com.qingqing.stock_analyse.service.StockCodeService;
import com.qingqing.stock_analyse.service.analyse.TZiTypeAnalyseService;
import org.springframework.stereotype.Service;

@Service
public class TZiTypeAnalyseServiceImpl implements TZiTypeAnalyseService {

	@Autowired
	private StockCodeService stockCodeService;
	@Autowired
	private StockInfoService stockInfoService;
	@Autowired
	private StockTZiTypeResultMapper stockTZiTypeResultMapper;

	public static final Logger logger = LoggerFactory.getLogger(TZiTypeAnalyseServiceImpl.class);

	@Override
	public Map<String, StockTZiTypeResult> findAllTZiTypeResult(Date date) {
		List<StockTZiTypeResult> list = stockTZiTypeResultMapper.findAllByDate(date);
		Map<String, StockTZiTypeResult> map = new HashMap<String, StockTZiTypeResult>();
		for (StockTZiTypeResult item : list) {
			map.put(item.getStockCode(), item);
		}
		return map;
	}

	@Override
	public void analyseTZiTypeResult(Date date) {
		List<String> stockCodes = stockCodeService.findAllStockCodes();
		Map<String, StockTiaoKongResult> map = new HashMap<String, StockTiaoKongResult>();
		for (String stockCode : stockCodes) {
			try {
				Date prevDate = StockDateUtil.findLastestOpenMarketkDayExceptToday(date);
				StockInfo stockInfo = stockInfoService.findByStockCodeAndDate(stockCode, date);
				StockInfo prevStockInfo = stockInfoService.findByStockCodeAndDate(stockCode, prevDate);
				analyseTZiTypeResult(date, stockInfo, prevStockInfo);
			} catch (Exception e) {
				logger.warn("analyseTiaoKongResult fail, date:{}, stockCode:{}, ex:{}", date, stockCode, e);
			}
		}
	}

	@Override
	public StockTZiTypeResult analyseTZiTypeResult(Date date, StockInfo stockInfo, StockInfo prevStockInfo) {
		String stockCode = stockInfo.getStockCode();

		double openPrice = stockInfo.getOpenPrice();
		double closePrice = stockInfo.getClosePrice();
		double maxPrice = stockInfo.getMaxPrice();
		double minPrice = stockInfo.getMinPrice();

		if (stockInfo.getIncreasePercent() > StockAnalyseConstants.INCEASE_TO_CEIL_PERCENT_LIMIT
				&& !DoubleCompareUtil.equals(minPrice, maxPrice)
				&& DoubleCompareUtil.equals(maxPrice, openPrice)
				&& DoubleCompareUtil.equals(maxPrice, closePrice)) {

			StockTZiTypeResult result = new StockTZiTypeResult();
			result.setDate(stockInfo.getDate());
			result.setNextDayIncreasePercent(null);
			result.setNextWeekIncreasePercent(null);
			result.setStockCode(stockCode);
			result.setCurrentMin(stockInfo.getMinPrice());
			result.setCurrentMax(stockInfo.getMaxPrice());

			if (prevStockInfo != null) {
				result.setPrevMax(prevStockInfo.getMaxPrice());
				result.setMinIncrPercent(StockPriceUtil.getIncreasePercent(prevStockInfo.getMaxPrice(), stockInfo.getMinPrice()));
			}
			stockTZiTypeResultMapper.insertIgnore(result);
			return result;
		}
		return null;
	}
}
