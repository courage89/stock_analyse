package com.qingqing.stock_analyse.service.analyse.impl;

import com.qingqing.common.util.converter.lang.DoubleCompareUtil;
import com.qingqing.stock_analyse.constants.StockAnalyseConstants;
import com.qingqing.stock_analyse.dao.analyse.StockPulseResultMapper;
import com.qingqing.stock_analyse.domain.StockBaseInfo;
import com.qingqing.stock_analyse.domain.result.StockPulseResult;
import com.qingqing.stock_analyse.service.StockBaseInfoService;
import com.qingqing.stock_analyse.service.StockCodeService;
import com.qingqing.stock_analyse.service.analyse.PulseAnalyseService;
import com.qingqing.stock_analyse.util.StockDateUtil;
import com.qingqing.stock_analyse.util.StockPriceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PulseAnalyseServiceImpl implements PulseAnalyseService {

    @Autowired
    private StockCodeService stockCodeService;
    @Autowired
    private StockBaseInfoService stockBaseInfoService;
    @Autowired
    private StockPulseResultMapper stockPulseResultMapper;

    @Override
    public Map<String, StockPulseResult> findAllPulseResult(Date date) {
        List<StockPulseResult> list = stockPulseResultMapper.findAllByDate(date);
        Map<String, StockPulseResult> map = new HashMap<String, StockPulseResult>();
        for (StockPulseResult item : list) {
            map.put(item.getStockCode(), item);
        }
        return map;
    }

    @Override
    public Map<String, StockPulseResult> analysePulseResult(Date date) {
        List<String> stockCodes = stockCodeService.findAllStockCodes();
        Map<String, StockPulseResult> map = new HashMap<String, StockPulseResult>();
        for (String stockCode : stockCodes) {
            StockPulseResult result = analysePulseResult(date, stockCode);
            if (result != null) {
                map.put(stockCode, result);
            }
        }
        return map;
    }

    @Override
    public StockPulseResult analysePulseResult(Date date, String stockCode) {
        StockBaseInfo lastStockBaseInfo = stockBaseInfoService.findByStockCodeAndDate(stockCode, StockDateUtil.findLastOpenMarketkDay(date));
        StockBaseInfo stockBaseInfo = stockBaseInfoService.findByStockCodeAndDate(stockCode, date);
        if (lastStockBaseInfo != null && lastStockBaseInfo != null) {
            Double openIncreasePercent = StockPriceUtil.getIncreasePercent(lastStockBaseInfo.getClosePrice(), stockBaseInfo.getOpenPrice());
            Double closeIncreasePercent = StockPriceUtil.getIncreasePercent(lastStockBaseInfo.getClosePrice(), stockBaseInfo.getClosePrice());
            Double maxIncreasePercent = StockPriceUtil.getIncreasePercent(lastStockBaseInfo.getClosePrice(), stockBaseInfo.getMaxPrice());

            if (DoubleCompareUtil.lt(openIncreasePercent, StockAnalyseConstants.OPEN_PRICE_INCREASE_PERCENT_LIMIT_IN_PULSE)
                    && DoubleCompareUtil.lt(closeIncreasePercent, StockAnalyseConstants.CLOSE_PRICE_INCREASE_PERCENT_LIMIT_IN_PULSE)
                    && DoubleCompareUtil.gt(maxIncreasePercent, StockAnalyseConstants.MAX_PRICE_INCREASE_PERCENT_LIMIT_IN_PULSE)) {

                StockPulseResult result = new StockPulseResult();
                result.setCloseIncreasePercent(closeIncreasePercent);
                result.setMaxIncreasePercent(maxIncreasePercent);
                result.setOpenIncreasePercent(openIncreasePercent);

                result.setStockCode(stockCode);
                result.setDate(date);
                result.setCreateTime(new Date());

                stockPulseResultMapper.insertIgnore(result);
                return result;
            }
        }
        return null;
    }
}
