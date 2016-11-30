package com.qingqing.stock_analyse.controller;

import com.qingqing.stock_analyse.domain.StockMarket;
import com.qingqing.stock_analyse.manager.StockAnalyseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xuya on 2016/11/30.
 */
@Controller
@RequestMapping("/stock_info/import")
public class StockInfoImportController {

    @Autowired
    private StockAnalyseManager stockAnalyseManager;

    @RequestMapping("from_juhe_data")
    public void importFromJuHeData(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        stockAnalyseManager.analyseStock(StockMarket.ShangHai);
        stockAnalyseManager.analyseStock(StockMarket.ShenZhen);
    }
}
