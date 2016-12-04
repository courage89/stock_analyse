package com.qingqing.stock_analyse.service.analyse.impl;

import com.qingqing.common.util.converter.lang.DoubleCompareUtil;
import com.qingqing.stock_analyse.dao.analyse.StockTiaoKongResultMapper;
import com.qingqing.stock_analyse.domain.StockInfo;
import com.qingqing.stock_analyse.domain.result.StockTiaoKongResult;
import com.qingqing.stock_analyse.service.StockInfoService;
import com.qingqing.stock_analyse.service.StockCodeService;
import com.qingqing.stock_analyse.service.analyse.TiaoKongAnalyseService;
import com.qingqing.stock_analyse.util.StockDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private StockInfoService stockInfoService;
    @Autowired
    private StockTiaoKongResultMapper stockTiaoKongResultMapper;
    
    public static final Logger logger = LoggerFactory.getLogger(TiaoKongAnalyseServiceImpl.class);

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
    public void analyseTiaoKongResult(Date date) {
        List<String> stockCodes = stockCodeService.findAllStockCodes();
        Map<String, StockTiaoKongResult> map = new HashMap<String, StockTiaoKongResult>();
        for (String stockCode : stockCodes) {
            try {
                Date prevDate = StockDateUtil.findLastOpenMarketkDay(date);
                StockInfo stockInfo = stockInfoService.findByStockCodeAndDate(stockCode, date);
                StockInfo prevStockInfo = stockInfoService.findByStockCodeAndDate(stockCode, prevDate);
                analyseTiaoKongResult(date, stockInfo, prevStockInfo);
            } catch (Exception e) {
                logger.warn("analyseTiaoKongResult fail, date:{}, stockCode:{}, ex:{}", date, stockCode, e);
            }
        }
    }

    @Override
    public StockTiaoKongResult analyseTiaoKongResult(Date date, StockInfo stockInfo, StockInfo prevStockInfo){
        String stockCode = stockInfo.getStockCode();
        if (prevStockInfo != null && StockInfo.isIncreaseToCeil(prevStockInfo)) {
            if (stockInfo != null && DoubleCompareUtil.gt(stockInfo.getMinPrice(), prevStockInfo.getMaxPrice())) {
                StockTiaoKongResult result = new StockTiaoKongResult();
                result.setPrevMax(prevStockInfo.getMaxPrice());
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
