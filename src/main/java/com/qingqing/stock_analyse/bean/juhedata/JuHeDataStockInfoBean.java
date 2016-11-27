package com.qingqing.stock_analyse.bean.juhedata;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by xuya on 2016/11/27.
 */
public class JuHeDataStockInfoBean {

//    {
//        "symbol": "sh600000",
//            "name": "浦发银行",
//            "trade": "17.090", //最新价, 15点之后即为收盘价
//            "pricechange": "0.200", //涨跌额
//            "changepercent": "1.184",//涨跌幅
//            "buy": "17.070",
//            "sell": "17.080",
//            "settlement": "16.890", //昨收
//            "open": "16.890",//开盘价
//            "high": "17.100",//最高价
//            "low": "16.810", //最低价
//            "volume": 233352, //成交量
//            "amount": 396231886, //成交额
//            "code": "600000", //股票简码
//            "ticktime": "15:00:00"
//    },

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("name")
    private String stockName;
    @JsonProperty("pricechange")
    private String priceChange;
    @JsonProperty("changepercent")
    private String priceChangePercent;
    @JsonProperty("settlement")
    private String prevClose;
    @JsonProperty("trade")
    private String currentPrice;
    @JsonProperty("open")
    private String openPrice;
    @JsonProperty("high")
    private String highPrice;
    @JsonProperty("low")
    private String minPrice;
    @JsonProperty("volume")
    private String allDealCnt;
    @JsonProperty("amount")
    private String allDealAmount;
    @JsonProperty("code")
    private String stockCode;
    @JsonProperty("ticktime")
    private String ticketTime;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setPriceChangePercent(String priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public String getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(String prevClose) {
        this.prevClose = prevClose;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getAllDealCnt() {
        return allDealCnt;
    }

    public void setAllDealCnt(String allDealCnt) {
        this.allDealCnt = allDealCnt;
    }

    public String getAllDealAmount() {
        return allDealAmount;
    }

    public void setAllDealAmount(String allDealAmount) {
        this.allDealAmount = allDealAmount;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(String ticketTime) {
        this.ticketTime = ticketTime;
    }
}
