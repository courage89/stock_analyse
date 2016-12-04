package com.qingqing.stock_analyse.manager;

import com.qingqing.stock_analyse.bean.juhedata.JuHeDataPageBean;
import com.qingqing.stock_analyse.bean.juhedata.JuHeDataStockInfoBean;
import com.qingqing.stock_analyse.constants.StockAnalyseConstants;
import com.qingqing.stock_analyse.converter.juhedata.JuHeDataConverter;
import com.qingqing.stock_analyse.domain.StockInfo;
import com.qingqing.stock_analyse.domain.StockMarket;
import com.qingqing.stock_analyse.domain.result.StockPulseResult;
import com.qingqing.stock_analyse.domain.result.StockYiZiBanResult;
import com.qingqing.stock_analyse.service.StockCodeService;
import com.qingqing.stock_analyse.service.StockInfoService;
import com.qingqing.stock_analyse.service.analyse.PulseAnalyseService;
import com.qingqing.stock_analyse.service.analyse.TZiTypeAnalyseService;
import com.qingqing.stock_analyse.service.analyse.TiaoKongAnalyseService;
import com.qingqing.stock_analyse.service.analyse.YiZiBanAnalyseService;
import com.qingqing.stock_analyse.util.StockDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockAnalyseManager {

    @Autowired
    private JuHeDataManager juHeDataManager;
    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private StockCodeService stockCodeService;
    @Autowired
    private PulseAnalyseService pulseAnalyseService;
    @Autowired
    private YiZiBanAnalyseService yiZiBanAnalyseService;
    @Autowired
    private TZiTypeAnalyseService tZiTypeAnalyseService;
    @Autowired
    private TiaoKongAnalyseService tiaoKongAnalyseService;

    public static final Logger logger = LoggerFactory.getLogger(StockAnalyseManager.class);
    
    public void saveStockInfo(StockMarket stockMarket) {
        Date stockDate = StockDateUtil.findLastOpenMarketkDay(new Date());
        int pageCnt = stockMarket.getCount() / StockAnalyseConstants.STOCK_CNT_PER_REQUEST;
        for (int pageNo = 0; pageNo < pageCnt; ++pageNo) {
            try {
                JuHeDataPageBean<JuHeDataStockInfoBean> pageBean = juHeDataManager.getStockeInfoBean(pageNo, stockMarket);
                List<StockInfo> stockInfos = JuHeDataConverter.convertJuHeDataBeanToStockInfo(pageBean, stockDate, stockMarket);
                stockInfoService.insertStockInfo(stockInfos);
            } catch (Exception ex) {
                logger.warn("save stock info error, stockMarket:" + stockMarket + "date:" + stockDate, ex);
            }
        }
        logger.info("finished save stock info, stockDate:{}, stockMarket:{}", stockDate, stockMarket);
    }

    public void analysePulseStock() {
        Date stockDate = StockDateUtil.findLastOpenMarketkDay(new Date());
        pulseAnalyseService.analysePulseResult(stockDate);
        yiZiBanAnalyseService.analyseYiZiBanResult(stockDate);
        tZiTypeAnalyseService.analyseTZiTypeResult(stockDate);
        tiaoKongAnalyseService.analyseTiaoKongResult(stockDate);
    }

    public void analyseStock(){
        Date stockDate = StockDateUtil.findLastOpenMarketkDay(new Date());
        List<String> stockCodes = stockCodeService.findAllStockCodes();
        Map<String, StockPulseResult> map = new HashMap<String, StockPulseResult>();
        for (String stockCode : stockCodes) {
            try {
                Date prevDate = StockDateUtil.findLastOpenMarketkDay(stockDate);
                StockInfo stockInfo = stockInfoService.findByStockCodeAndDate(stockCode, stockDate);
                StockInfo prevStockInfo = stockInfoService.findByStockCodeAndDate(stockCode, prevDate);
                pulseAnalyseService.analysePulseResult(stockDate, stockInfo, prevStockInfo);
                yiZiBanAnalyseService.analyseYiZiBanResult(stockDate, stockInfo);
                tZiTypeAnalyseService.analyseTZiTypeResult(stockDate, stockInfo, prevStockInfo);
                tiaoKongAnalyseService.analyseTiaoKongResult(stockDate, stockInfo, prevStockInfo);
            } catch (Exception e) {
                logger.warn("analyse stock fail, stockDate:{}, stockCode:{}, ex:{}", stockDate, stockCode, e);
            }
        }
    }
}