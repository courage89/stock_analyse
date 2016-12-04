package com.qingqing.stock_analyse.dao.impl;

import com.qingqing.stock_analyse.TestBase;
import com.qingqing.stock_analyse.dao.analyse.StockTZiTypeResultMapper;
import com.qingqing.stock_analyse.domain.result.StockTZiTypeResult;
import com.qingqing.stock_analyse.util.StockDateUtil;
import com.qingqing.stock_analyse.util.UnitTestEqualsUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by xuya on 2016/12/4.
 */
public class StockTZiResultMapperTest extends TestBase {

    private Double preMax = 34.22d;
    private Double currentMin = 23.23d;
    private Double currentMax = 33.23d;
    private Double minIncrPercent = 23.53d;
    private String stockCode = "ABCDEFG";
    private Double nextDayIncreasePercent = 23.13d;
    private Double nextWeekIncreasePercent = 34.22d;
    private Date date = StockDateUtil.getDate(2016, 1, 1);

    @Autowired
    private StockTZiTypeResultMapper stockTZiTypeResultMapper;

    @Test
    public void testInsert() throws IllegalAccessException {
        StockTZiTypeResult record = getStockTZiTypeResult();
        stockTZiTypeResultMapper.insertIgnore(record);
        StockTZiTypeResult record2 = stockTZiTypeResultMapper.selectByPrimaryKey(record.getId());
        Assert.assertTrue(UnitTestEqualsUtil.isEquals(record, record2, "createTime", "lastUpdateTime"));
    }

    private StockTZiTypeResult getStockTZiTypeResult() {
        StockTZiTypeResult record = new StockTZiTypeResult();
        record.setMinIncrPercent(minIncrPercent);
        record.setCurrentMax(currentMax);
        record.setDate(date);
        record.setPrevMax(preMax);
        record.setStockCode(stockCode);
        record.setCurrentMin(currentMin);
        record.setNextDayIncreasePercent(nextDayIncreasePercent);
        record.setNextWeekIncreasePercent(nextWeekIncreasePercent);
        return record;
    }
}
