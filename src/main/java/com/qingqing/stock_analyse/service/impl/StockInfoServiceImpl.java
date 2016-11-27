package com.qingqing.stock_analyse.service.impl;

import com.qingqing.stock_analyse.dao.analyse.StockInfoMapper;
import com.qingqing.stock_analyse.domain.StockInfo;
import com.qingqing.stock_analyse.service.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by xuya on 2016/11/27.
 */
@Service
public class StockInfoServiceImpl implements StockInfoService{

    @Autowired
    private StockInfoMapper stockInfoMapper;

    @Override
    public StockInfo findByStockCodeAndDate(String stockCode, Date stockDate) {
        return stockInfoMapper.findByStockCodeAndDate(stockCode, stockDate);
    }

    @Override
    public List<StockInfo> findByStockCodeAndTimeDuration(String stockCode, Date startDate, Date endDate) {
        return stockInfoMapper.findByStockCodeAndDateRange(stockCode, startDate, endDate);
    }

    @Override
    public void insertStockInfo(List<StockInfo> stockInfos) {
        stockInfoMapper.batchInsert(stockInfos);
    }
}
