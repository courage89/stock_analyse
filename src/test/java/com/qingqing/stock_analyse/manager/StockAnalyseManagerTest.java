package com.qingqing.stock_analyse.manager;

import com.qingqing.stock_analyse.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xuya on 2016/11/27.
 */
public class StockAnalyseManagerTest extends TestBase{
    
    @Autowired
    private StockAnalyseManager stockAnalyseManager;
    
    @Test
    public void testAnalyse(){
        stockAnalyseManager.analyseSHStock();
    }
}
