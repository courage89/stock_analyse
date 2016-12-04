package com.qingqing.stock_analyse.service.analyse.impl;

import com.qingqing.common.util.converter.lang.DoubleCompareUtil;
import com.qingqing.stock_analyse.constants.StockAnalyseConstants;
import com.qingqing.stock_analyse.dao.analyse.StockPulseResultMapper;
import com.qingqing.stock_analyse.domain.StockInfo;
import com.qingqing.stock_analyse.domain.result.StockPulseResult;
import com.qingqing.stock_analyse.service.StockInfoService;
import com.qingqing.stock_analyse.service.StockCodeService;
import com.qingqing.stock_analyse.service.analyse.PulseAnalyseService;
import com.qingqing.stock_analyse.util.StockDateUtil;
import com.qingqing.stock_analyse.util.StockPriceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private StockInfoService stockInfoService;
    @Autowired
    private StockPulseResultMapper stockPulseResultMapper;

    public static final Logger logger = LoggerFactory.getLogger(PulseAnalyseServiceImpl.class);

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
    public void analysePulseResult(Date date) {
        List<String> stockCodes = stockCodeService.findAllStockCodes();
        Map<String, StockPulseResult> map = new HashMap<String, StockPulseResult>();
        for (String stockCode : stockCodes) {
            try {
                Date prevDate = StockDateUtil.findLastOpenMarketkDay(date);
                StockInfo stockInfo = stockInfoService.findByStockCodeAndDate(stockCode, date);
                StockInfo prevStockInfo = stockInfoService.findByStockCodeAndDate(stockCode, prevDate);
                analysePulseResult(date, stockInfo, prevStockInfo);
            } catch (Exception e) {
                logger.warn("analyse pulse result fail, date:{}, stockCode:{}, ex:{}", date, stockCode, e);
            }
        }
    }

    @Override
    public StockPulseResult analysePulseResult(Date date, StockInfo stockInfo, StockInfo prevStockInfo) {
        String stockCode = stockInfo.getStockCode();
        if (prevStockInfo != null && prevStockInfo != null) {
            Double openIncreasePercent = StockPriceUtil.getIncreasePercent(prevStockInfo.getClosePrice(), stockInfo.getOpenPrice());
            Double closeIncreasePercent = StockPriceUtil.getIncreasePercent(prevStockInfo.getClosePrice(), stockInfo.getClosePrice());
            Double maxIncreasePercent = StockPriceUtil.getIncreasePercent(prevStockInfo.getClosePrice(), stockInfo.getMaxPrice());

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
