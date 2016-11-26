package com.qingqing.stock_analyse.service.analyse.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingqing.notes_common.util.DoubleCompareUtil;
import com.qingqing.stock_analyse.constants.StockAnalyseConstants;
import com.qingqing.stock_analyse.dao.analyse.YiZiBanAnalyseMapper;
import com.qingqing.stock_analyse.domain.StockBaseInfo;
import com.qingqing.stock_analyse.domain.result.StockYiZiBanResult;
import com.qingqing.stock_analyse.service.StockBaseInfoService;
import com.qingqing.stock_analyse.service.StockCodeService;
import com.qingqing.stock_analyse.service.analyse.YiZiBanAnalyseService;

@Service
public class YiZiBanAnalyseServiceImpl implements YiZiBanAnalyseService {

	@Autowired
	private StockCodeService stockCodeService;
	@Autowired
	private StockBaseInfoService stockBaseInfoService;
	
	@Autowired
	private YiZiBanAnalyseMapper stockIncreaseCeilAnalyseMapper;
	
	@Override
	public Map<String, StockYiZiBanResult> findAllYiZiBanResult(Date date) {
		List<StockYiZiBanResult> list = stockIncreaseCeilAnalyseMapper.findAllByDate(date);
		Map<String,StockYiZiBanResult> map = new HashMap<String, StockYiZiBanResult>();
		for(StockYiZiBanResult item : list){
			map.put(item.getStockCode(), item);
		}
		return map;
	}

	@Override
	public Map<String, StockYiZiBanResult> analyseYiZiBanResult(Date date) {
		List<String> stockCodes = stockCodeService.findAllStockCodes();
		Map<String ,StockYiZiBanResult> map = new HashMap<String, StockYiZiBanResult>();
		for(String stockCode : stockCodes){
			StockYiZiBanResult result = analyseYiZiBanResult(date, stockCode);
			if(result != null){
				map.put(stockCode, result);
			}
		}
		return map;
	}

	@Override
	public StockYiZiBanResult analyseYiZiBanResult(Date date, String stockCode) {
		StockBaseInfo stockBaseInfo = stockBaseInfoService.findByStockCodeAndDate(stockCode, date);
		double openPrice = stockBaseInfo.getOpenPrice();
		double closePrice = stockBaseInfo.getClosePrice();
		double maxPrice = stockBaseInfo.getMaxPrice();
		double minPrice = stockBaseInfo.getMinPrice();
		
		if( stockBaseInfo.getIncreasePercent() > StockAnalyseConstants.INCEASE_TO_CEIL_PERCENT_LIMIT 
				&& DoubleCompareUtil.equals(openPrice, closePrice) 
				&& DoubleCompareUtil.equals(maxPrice, minPrice) 
				&& DoubleCompareUtil.equals(openPrice, maxPrice)){
			
			StockYiZiBanResult result = new StockYiZiBanResult();
			result.setDate(stockBaseInfo.getDate());
			result.setNextDayIncreasePercent(null);
			result.setNextWeekIncreasePercent(null);
			result.setStockCode(stockCode);
			
			stockIncreaseCeilAnalyseMapper.insertIngore(result);
			return result;
		}
		return null;
	}
}
