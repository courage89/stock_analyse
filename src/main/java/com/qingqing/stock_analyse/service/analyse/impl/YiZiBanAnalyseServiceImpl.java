package com.qingqing.stock_analyse.service.analyse.impl;

import com.qingqing.common.util.converter.lang.DoubleCompareUtil;
import com.qingqing.stock_analyse.constants.StockAnalyseConstants;
import com.qingqing.stock_analyse.dao.analyse.StockYiZiBanResultMapper;
import com.qingqing.stock_analyse.domain.StockInfo;
import com.qingqing.stock_analyse.domain.result.StockYiZiBanResult;
import com.qingqing.stock_analyse.service.StockInfoService;
import com.qingqing.stock_analyse.service.StockCodeService;
import com.qingqing.stock_analyse.service.analyse.YiZiBanAnalyseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YiZiBanAnalyseServiceImpl implements YiZiBanAnalyseService {

    @Autowired
    private StockCodeService stockCodeService;
    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private StockYiZiBanResultMapper stockYiZiBanResultMapper;

    public static final Logger logger = LoggerFactory.getLogger(YiZiBanAnalyseServiceImpl.class);
    
    @Override
    public Map<String, StockYiZiBanResult> findAllYiZiBanResult(Date date) {
        List<StockYiZiBanResult> list = stockYiZiBanResultMapper.findAllByDate(date);
        Map<String, StockYiZiBanResult> map = new HashMap<String, StockYiZiBanResult>();
        for (StockYiZiBanResult item : list) {
            map.put(item.getStockCode(), item);
        }
        return map;
    }

    @Override
    public void analyseYiZiBanResult(Date date) {
        List<String> stockCodes = stockCodeService.findAllStockCodes();
        Map<String, StockYiZiBanResult> map = new HashMap<String, StockYiZiBanResult>();
        for (String stockCode : stockCodes) {
            try {
                StockInfo stockInfo = stockInfoService.findByStockCodeAndDate(stockCode, date);
                analyseYiZiBanResult(date, stockInfo);
            } catch (Exception e) {
                logger.warn("analyseYiZiBan fail, date:{}, stockCode:{}, ex:{}", date, stockCode, e);
            }
        }
    }

    @Override
    public StockYiZiBanResult analyseYiZiBanResult(Date date, StockInfo stockInfo) {
        String stockCode = stockInfo.getStockCode();
        double openPrice = stockInfo.getOpenPrice();
        double closePrice = stockInfo.getClosePrice();
        double maxPrice = stockInfo.getMaxPrice();
        double minPrice = stockInfo.getMinPrice();

        if (stockInfo.getIncreasePercent() > StockAnalyseConstants.INCEASE_TO_CEIL_PERCENT_LIMIT
                && DoubleCompareUtil.equals(openPrice, closePrice)
                && DoubleCompareUtil.equals(maxPrice, minPrice)
                && DoubleCompareUtil.equals(openPrice, maxPrice)) {
            StockYiZiBanResult result = new StockYiZiBanResult();
            result.setPrice(maxPrice);
            result.setStockCode(stockCode);
            result.setDate(stockInfo.getDate());
            result.setNextDayIncreasePercent(null);
            result.setNextWeekIncreasePercent(null);
            stockYiZiBanResultMapper.insertIgnore(result);
            return result;
        }
        return null;
    }
}
