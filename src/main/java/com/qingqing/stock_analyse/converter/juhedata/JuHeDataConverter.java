package com.qingqing.stock_analyse.converter.juhedata;

import com.qingqing.common.util.converter.lang.DoubleConvert;
import com.qingqing.common.util.converter.lang.IntegerConvert;
import com.qingqing.stock_analyse.bean.juhedata.JuHeDataPageBean;
import com.qingqing.stock_analyse.bean.juhedata.JuHeDataStockInfoBean;
import com.qingqing.stock_analyse.domain.StockInfo;
import com.qingqing.stock_analyse.domain.StockMarket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xuya on 2016/11/27.
 */
public class JuHeDataConverter {

    /**
     * 1、暂时按照周一到周五开市来判断
     */
    public static final List<StockInfo> convertJuHeDataBeanToStockInfo(JuHeDataPageBean<JuHeDataStockInfoBean> bean, Date stockDate, StockMarket stockMarket) {
        List<JuHeDataStockInfoBean> juheDataStockInfoBeans = bean.getData();
        List<StockInfo> stockInfos = new ArrayList<StockInfo>(juheDataStockInfoBeans.size());
        for (JuHeDataStockInfoBean infoBean : juheDataStockInfoBeans) {
            StockInfo stockInfo = new StockInfo();
            stockInfo.setAllCnt(null);
            stockInfo.setAllPrice(null);
            stockInfo.setDate(stockDate);
            stockInfo.setWavePercent(null);
            stockInfo.setExchangePercent(null);
            stockInfo.setStockMarket(stockMarket);
            stockInfo.setStockCode(infoBean.getStockCode());
            stockInfo.setStockName(infoBean.getStockName());
            stockInfo.setMaxPrice(DoubleConvert.getConvert().getValue(infoBean.getHighPrice()));
            stockInfo.setMinPrice(DoubleConvert.getConvert().getValue(infoBean.getMinPrice()));
            stockInfo.setOpenPrice(DoubleConvert.getConvert().getValue(infoBean.getOpenPrice()));
            stockInfo.setDealCnt(IntegerConvert.getConvert().getValue(infoBean.getAllDealCnt()));
            stockInfo.setClosePrice(DoubleConvert.getConvert().getValue(infoBean.getCurrentPrice()));
            stockInfo.setIncreasePercent(DoubleConvert.getConvert().getValue(infoBean.getPriceChangePercent()));
            stockInfos.add(stockInfo);
        }
        return stockInfos;
    }
}
