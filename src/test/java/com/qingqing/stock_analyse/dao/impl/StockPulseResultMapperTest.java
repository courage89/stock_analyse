package com.qingqing.stock_analyse.dao.impl;

import com.qingqing.stock_analyse.TestBase;
import com.qingqing.stock_analyse.dao.analyse.StockPulseResultMapper;
import com.qingqing.stock_analyse.domain.result.StockPulseResult;
import com.qingqing.stock_analyse.util.StockDateUtil;
import com.qingqing.stock_analyse.util.UnitTestEqualsUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by xuya on 2016/12/4.
 */
public class StockPulseResultMapperTest extends TestBase {

    private Double closeIncreasePercent = 20.02d;
    private Double maxIncreasePercent = 23.12d;
    private Double openIncreasePercent = 32.12d;
    private Double nextDayIncreasePercent = 23.13d;
    private Double nextWeekIncreasePercent = 34.22d;
    private String stockCode = "ABCDEFG";
    private Date date = StockDateUtil.getDate(2016, 1, 1);

    @Autowired
    private StockPulseResultMapper stockPulseResultMapper;

    @Test
    public void testInsert() throws IllegalAccessException {
        StockPulseResult record = getStockPulseResult();
        stockPulseResultMapper.insertIgnore(record);

        StockPulseResult record2 = stockPulseResultMapper.selectByPrimaryKey(record.getId());

        Assert.assertTrue(UnitTestEqualsUtil.isEquals(record, record2, "createTime", "lastUpdateTime"));
    }

    private StockPulseResult getStockPulseResult() {
        StockPulseResult record = new StockPulseResult();
        record.setCloseIncreasePercent(closeIncreasePercent);
        record.setMaxIncreasePercent(maxIncreasePercent);
        record.setOpenIncreasePercent(openIncreasePercent);
        record.setDate(date);
        record.setNextDayIncreasePercent(nextDayIncreasePercent);
        record.setNextWeekIncreasePercent(nextWeekIncreasePercent);
        record.setStockCode(stockCode);
        return record;
    }


}
