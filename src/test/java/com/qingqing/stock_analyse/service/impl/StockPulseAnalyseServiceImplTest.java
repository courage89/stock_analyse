package com.qingqing.stock_analyse.service.impl;

import com.qingqing.stock_analyse.TestBase;
import com.qingqing.stock_analyse.service.analyse.PulseAnalyseService;
import com.qingqing.stock_analyse.util.StockDateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by xuya on 2016/12/4.
 */
public class StockPulseAnalyseServiceImplTest extends TestBase {

    @Autowired
    private PulseAnalyseService pulseAnalyseService;

    @Test
    public void testPulseAnalyse() {
        Date date = StockDateUtil.findLastestOpenMarketkDay(new Date());
        pulseAnalyseService.analysePulseResult(date);
    }
}


