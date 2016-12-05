package com.qingqing.stock_analyse.service.impl;

import com.qingqing.stock_analyse.TestBase;
import com.qingqing.stock_analyse.domain.StockInfo;
import com.qingqing.stock_analyse.service.StockInfoService;
import com.qingqing.stock_analyse.service.analyse.YiZiBanAnalyseService;
import com.qingqing.stock_analyse.util.StockDateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by xuya on 2016/12/4.
 */
public class StockYiZiBanAnalyseServiceImplTest extends TestBase {

    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private YiZiBanAnalyseService yiZiBanAnalyseService;

    @Test
    public void testYiZiBanAnalyse() {
        Date date = StockDateUtil.findLastestOpenMarketkDay(new Date());
        yiZiBanAnalyseService.analyseYiZiBanResult(date);
    }

    @Test
    public void testYiZiBanAnalayse2() {
        List<String> stockCodes = Arrays.asList("601882","603633","002059","002389","002819","002820","002821","002822","002823","002825","300376");
        Date date = StockDateUtil.findLastestOpenMarketkDay(new Date());
        for (String stockCode : stockCodes) {
            StockInfo stockInfo = stockInfoService.findByStockCodeAndDate(stockCode, date);
            yiZiBanAnalyseService.analyseYiZiBanResult(date, stockInfo);
        }
    }
}
