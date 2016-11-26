package com.qingqing.stock_analyse.service.analyse.impl;

import com.qingqing.common.util.converter.lang.DoubleCompareUtil;
import com.qingqing.stock_analyse.dao.analyse.StockTiaoKongResultMapper;
import com.qingqing.stock_analyse.domain.StockInfo;
import com.qingqing.stock_analyse.domain.result.StockTiaoKongResult;
import com.qingqing.stock_analyse.service.StockBaseInfoService;
import com.qingqing.stock_analyse.service.StockCodeService;
import com.qingqing.stock_analyse.service.analyse.TiaoKongAnalyseService;
import com.qingqing.stock_analyse.util.StockDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TiaoKongAnalyseServiceImpl implements TiaoKongAnalyseService {

    @Autowired
    private StockCodeService stockCodeService;
    @Autowired
    private StockBaseInfoService stockBaseInfoService;
    @Autowired
    private StockTiaoKongResultMapper stockTiaoKongResultMapper;

    @Override
    public Map<String, StockTiaoKongResult> findAllTiaoKongResult(Date date) {
        List<StockTiaoKongResult> list = stockTiaoKongResultMapper.findAllByDate(date);
        Map<String, StockTiaoKongResult> map = new HashMap<String, StockTiaoKongResult>();
        for (StockTiaoKongResult item : list) {
            map.put(item.getStockCode(), item);
        }
        return map;
    }

    @Override
    public Map<String, StockTiaoKongResult> analyseTiaoKongResult(Date date) {
        List<String> stockCodes = stockCodeService.findAllStockCodes();
        Map<String, StockTiaoKongResult> map = new HashMap<String, StockTiaoKongResult>();
        for (String stockCode : stockCodes) {
            StockTiaoKongResult result = analyseTiaoKongResult(date, stockCode);
            if (result != null) {
                map.put(stockCode, result);
            }
        }
        return map;
    }

    @Override
    public StockTiaoKongResult analyseTiaoKongResult(Date date, String stockCode) {

        StockInfo lastStockInfo = stockBaseInfoService.findByStockCodeAndDate(stockCode, StockDateUtil.findLastOpenMarketkDay(date));
        if (lastStockInfo != null && StockInfo.isIncreaseToCeil(lastStockInfo)) {
            StockInfo stockInfo = stockBaseInfoService.findByStockCodeAndDate(stockCode, date);
            if (stockInfo != null && DoubleCompareUtil.gt(stockInfo.getMinPrice(), lastStockInfo.getMaxPrice())) {
                StockTiaoKongResult result = new StockTiaoKongResult();
                result.setPrevMax(lastStockInfo.getMaxPrice());
                result.setDate(date);
                result.setCreateTime(new Date());
                result.setCurrentMin(stockInfo.getMinPrice());
                result.setStockCode(stockCode);

                result.setNextDayIncreasePercent(null);
                result.setNextWeekIncreasePercent(null);
                stockTiaoKongResultMapper.insertIgnore(result);
                return result;
            }
        }
        return null;
    }

}
