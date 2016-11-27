package com.qingqing.stock_analyse.manager;

import com.qingqing.stock_analyse.bean.juhedata.JuHeDataPageBean;
import com.qingqing.stock_analyse.bean.juhedata.JuHeDataStockInfoBean;
import com.qingqing.stock_analyse.constants.StockAnalyseConstants;
import com.qingqing.stock_analyse.converter.juhedata.JuHeDataConverter;
import com.qingqing.stock_analyse.domain.StockInfo;
import com.qingqing.stock_analyse.service.StockInfoService;
import com.qingqing.stock_analyse.util.StockDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class StockAnalyseManager {

    @Autowired
    private JuHeDataManager juHeDataManager;
    @Autowired
    private StockInfoService stockInfoService;

    public static final Logger logger = LoggerFactory.getLogger(StockAnalyseManager.class);
    
    public void analyseSHStock() {
        Date stockDate = StockDateUtil.findLastOpenMarketkDay(new Date());
        int pageCnt = StockAnalyseConstants.SH_STOCK_CNT / StockAnalyseConstants.STOCK_CNT_PER_REQUEST;
        for (int pageNo = 0; pageNo < pageCnt; ++pageNo) {
            try {
                JuHeDataPageBean<JuHeDataStockInfoBean> pageBean = juHeDataManager.getStockeInfoBeanForSH(pageNo);
                List<StockInfo> stockInfos = JuHeDataConverter.convertJuHeDataBeanToStockInfo(pageBean, stockDate);
                stockInfoService.insertStockInfo(stockInfos);
            } catch (Exception ex) {
                logger.warn("analyse SH error, date:" + stockDate, ex);
            }
        }
    }

    public void analyseSZStock() {
        Date stockDate = StockDateUtil.findLastOpenMarketkDay(new Date());
        int pageCnt = StockAnalyseConstants.SZ_STOCK_CNT / StockAnalyseConstants.STOCK_CNT_PER_REQUEST;
        for (int pageNo = 0; pageNo < pageCnt; ++pageNo) {
            try {
                JuHeDataPageBean<JuHeDataStockInfoBean> pageBean = juHeDataManager.getStockeInfoBeanForSZ(pageNo);
                List<StockInfo> stockInfos = JuHeDataConverter.convertJuHeDataBeanToStockInfo(pageBean, stockDate);
                stockInfoService.insertStockInfo(stockInfos);
            } catch (Exception ex) {
                logger.warn("analyse SH error, date:" + stockDate, ex);
            }
        }
    }
}
