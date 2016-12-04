package com.qingqing.stock_analyse.service.impl;

import com.qingqing.stock_analyse.dao.analyse.StockCodeMapper;
import com.qingqing.stock_analyse.domain.StockCode;
import com.qingqing.stock_analyse.service.StockCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuya on 2016/12/3.
 */
@Service
public class StockCodeServiceImpl implements StockCodeService {

    @Autowired
    private StockCodeMapper stockCodeMapper;

    @Override
    public Map<String, String> findAllStockMapping() {
        List<StockCode> stockCodes = stockCodeMapper.selectAll();
        Map<String, String> map = new HashMap<String, String>();
        for (StockCode stockCode: stockCodes) {
            map.put(stockCode.getStockCode(), stockCode.getStockName());
        }
        return map;
    }

    @Override
    public List<String> findAllStockCodes() {
        List<StockCode> stockCodes = stockCodeMapper.selectAll();
        List<String> list = new ArrayList<String>();
        for (StockCode stockCode : stockCodes) {
            list.add(stockCode.getStockCode());
        }
        return list;
    }
}
