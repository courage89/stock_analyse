package com.qingqing.stock_analyse.dao.impl;

import com.qingqing.stock_analyse.TestBase;
import com.qingqing.stock_analyse.dao.analyse.StockInfoMapper;
import com.qingqing.stock_analyse.domain.StockInfo;
import com.qingqing.stock_analyse.util.UnitTestEqualsUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by xuya on 2016/11/27.
 */
@Transactional
public class StockInfoMapperTest extends TestBase {

    private String stockCode = "asdfja";
    private String stockName = "sdafsfd";
    private Date date = new Date();
    private Double wavePercent = 11.23d;
    private Double closePrice = 12.23d;
    private Long allCnt = 23L;
    private Double allPrice = 13.23d;
    private Integer dealCnt = 230;
    private Double exchangePercent = 13.23d;
    private Double increasePercent = 14.23d;
    private Double maxPrice = 15.23d;
    private Double minPrice = 16.23d;
    private Double openPrice = 17.23d;

    @Autowired
    private StockInfoMapper stockInfoMapper;

    @Test
    public void testInsert() throws IllegalAccessException {
        StockInfo stockInfo = getStockInfo();
        stockInfoMapper.insert(stockInfo);
        StockInfo stockInfo2 = stockInfoMapper.selectByPrimaryKey(stockInfo.getId());
        Assert.assertTrue(UnitTestEqualsUtil.isEquals(stockInfo, stockInfo2, "date", "createTime", "lastUpdateTime"));
    }

    public StockInfo getStockInfo() {
        StockInfo stockInfo = new StockInfo();
        stockInfo.setStockCode(stockCode);
        stockInfo.setStockName(stockName);
        stockInfo.setDate(date);
        stockInfo.setWavePercent(wavePercent);
        stockInfo.setClosePrice(closePrice);
        stockInfo.setAllCnt(allCnt);
        stockInfo.setAllPrice(allPrice);
        stockInfo.setDealCnt(dealCnt);
        stockInfo.setExchangePercent(exchangePercent);
        stockInfo.setIncreasePercent(increasePercent);
        stockInfo.setMaxPrice(maxPrice);
        stockInfo.setMinPrice(minPrice);
        stockInfo.setOpenPrice(openPrice);
        return stockInfo;
    }
}
