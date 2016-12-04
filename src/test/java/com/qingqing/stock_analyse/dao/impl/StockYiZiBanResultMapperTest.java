package com.qingqing.stock_analyse.dao.impl;

import com.qingqing.stock_analyse.TestBase;
import com.qingqing.stock_analyse.dao.analyse.StockYiZiBanResultMapper;
import com.qingqing.stock_analyse.domain.result.StockTZiTypeResult;
import com.qingqing.stock_analyse.domain.result.StockYiZiBanResult;
import com.qingqing.stock_analyse.util.StockDateUtil;
import com.qingqing.stock_analyse.util.UnitTestEqualsUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by xuya on 2016/12/4.
 */
public class StockYiZiBanResultMapperTest extends TestBase {

    private Double price = 23.2d;
    private String stockCode = "ABCDEFG";
    private Double nextDayIncreasePercent = 23.13d;
    private Double nextWeekIncreasePercent = 34.22d;
    private Date date = StockDateUtil.getDate(2016, 1, 1);

    @Autowired
    private StockYiZiBanResultMapper stockYiZiBanResultMapper;

    @Test
    public void testInsert() throws IllegalAccessException {
        StockYiZiBanResult record = getStockYiZiBanResult();
        stockYiZiBanResultMapper.insertIgnore(record);
        StockYiZiBanResult record2 = stockYiZiBanResultMapper.selectByPrimaryKey(record.getId());
        Assert.assertTrue(UnitTestEqualsUtil.isEquals(record, record2, "createTime", "lastUpdateTime"));
    }

    private StockYiZiBanResult getStockYiZiBanResult() {
        StockYiZiBanResult record = new StockYiZiBanResult();
        record.setPrice(price);
        record.setDate(date);
        record.setStockCode(stockCode);
        record.setNextDayIncreasePercent(nextDayIncreasePercent);
        record.setNextWeekIncreasePercent(nextWeekIncreasePercent);
        return record;
    }
}
