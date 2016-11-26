package com.qingqing.stock_analyse.service.analyse.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qingqing.notes_common.util.DoubleCompareUtil;
import com.qingqing.stock_analyse.dao.analyse.TiaoKongAnalyseMapper;
import com.qingqing.stock_analyse.domain.StockBaseInfo;
import com.qingqing.stock_analyse.domain.result.StockTiaoKongResult;
import com.qingqing.stock_analyse.service.StockBaseInfoService;
import com.qingqing.stock_analyse.service.StockCodeService;
import com.qingqing.stock_analyse.service.analyse.TiaoKongAnalyseService;
import com.qingqing.stock_analyse.util.StockDateUtil;

@Service
public class TiaoKongAnalyseServiceImpl implements TiaoKongAnalyseService {

	@Autowired
	private StockCodeService stockCodeService;
	@Autowired
	private StockBaseInfoService stockBaseInfoService;
	@Autowired
	private TiaoKongAnalyseMapper tiaoKongAnalyseMapper;
	
	@Override
	public Map<String, StockTiaoKongResult> findAllTiaoKongResult(Date date) {
		List<StockTiaoKongResult> list = tiaoKongAnalyseMapper.findAllByDate(date);
		Map<String,StockTiaoKongResult> map = new HashMap<String, StockTiaoKongResult>();
		for(StockTiaoKongResult item : list){
			map.put(item.getStockCode(), item);
		}
		return map;
	}

	@Override
	public Map<String, StockTiaoKongResult> analyseTiaoKongResult(Date date) {
		List<String> stockCodes = stockCodeService.findAllStockCodes();
		Map<String ,StockTiaoKongResult> map = new HashMap<String, StockTiaoKongResult>();
		for(String stockCode : stockCodes){
			StockTiaoKongResult result = analyseTiaoKongResult(date, stockCode);
			if(result != null){
				map.put(stockCode, result);
			}
		}
		return map;
	}

	@Override
	public StockTiaoKongResult analyseTiaoKongResult(Date date, String stockCode) {
		
		StockBaseInfo lastStockBaseInfo = stockBaseInfoService.findByStockCodeAndDate(stockCode, StockDateUtil.findLastOpenMarketkDay(date));
		if(lastStockBaseInfo != null && StockBaseInfo.isIncreaseToCeil(lastStockBaseInfo)){
			StockBaseInfo stockBaseInfo = stockBaseInfoService.findByStockCodeAndDate(stockCode, date);
			if(stockBaseInfo != null && DoubleCompareUtil.gt(stockBaseInfo.getMinPrice(),lastStockBaseInfo.getMaxPrice())){
				StockTiaoKongResult result = new StockTiaoKongResult();
				result.setYesterdayCeilPrice(lastStockBaseInfo.getMaxPrice());
				result.setDate(date);
				result.setCreateTime(new Date());
				result.setTodayMinPrice(stockBaseInfo.getMinPrice());
				result.setStockCode(stockCode);
				
				result.setNextDayIncreasePercent(null);
				result.setNextWeekIncreasePercent(null);
				tiaoKongAnalyseMapper.insert(result);
				return result;
			}
		}
		return null;
	}

}
