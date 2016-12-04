package com.qingqing.stock_analyse.dao.impl;

import com.qingqing.stock_analyse.TestBase;
import com.qingqing.stock_analyse.dao.analyse.StockTiaoKongResultMapper;
import com.qingqing.stock_analyse.domain.result.StockPulseResult;
import com.qingqing.stock_analyse.domain.result.StockTiaoKongResult;
import com.qingqing.stock_analyse.util.StockDateUtil;
import com.qingqing.stock_analyse.util.UnitTestEqualsUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by xuya on 2016/12/4.
 */
public class StockTiaoKongResultMapperTest extends TestBase {

    private Double preMax = 34.22d;
    private Double currentMin = 23.23d;
    private String stockCode = "ABCDEFG";
    private Double nextDayIncreasePercent = 23.13d;
    private Double nextWeekIncreasePercent = 34.22d;
    private Date date = StockDateUtil.getDate(2016, 1, 1);

    @Autowired
    private StockTiaoKongResultMapper stockTiaoKongResultMapper;

    @Test
    public void testInsert() throws IllegalAccessException {
        StockTiaoKongResult record = getStockTiaoKongResult();
        stockTiaoKongResultMapper.insertIgnore(record);
        StockTiaoKongResult record2 = stockTiaoKongResultMapper.selectByPrimaryKey(record.getId());
        Assert.assertTrue(UnitTestEqualsUtil.isEquals(record, record2, "createTime", "lastUpdateTime"));
    }

    private StockTiaoKongResult getStockTiaoKongResult() {
        StockTiaoKongResult record = new StockTiaoKongResult();
        record.setDate(date);
        record.setPrevMax(preMax);
        record.setStockCode(stockCode);
        record.setCurrentMin(currentMin);
        record.setNextDayIncreasePercent(nextDayIncreasePercent);
        record.setNextWeekIncreasePercent(nextWeekIncreasePercent);
        return record;
    }
}
