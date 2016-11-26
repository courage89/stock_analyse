package com.qingqing.stock_analyse.domain;

import java.sql.Timestamp;
import java.util.Date;

import com.qingqing.notes_common.util.DoubleCompareUtil;
import com.qingqing.stock_analyse.constants.StockAnalyseConstants;

public class StockBaseInfo {

	/**
	 * 股票代码
	 */
	private String stockCode;
	
	/**
	 * 股票日期
	 */
	private Date date;
	
	/**
	 * 开盘价
	 */
	private Double openPrice;
	
	/**
	 * 最高价
	 */
	private Double maxPrice;
	
	/**
	 * 最低价
	 */
	private Double minPrice;
	
	/**
	 * 收盘价
	 */
	private Double closePrice;
	
	/**
	 * 涨幅
	 */
	private Double increasePercent;
	
	/**
	 * 振幅
	 */
	private Double wavePercent;
	
	/**
	 * 总手
	 */
	private Long allCnt;
	
	/**
	 * 总价
	 */
	private Double allPrice;
	
	/**
	 * 换手率
	 */
	private Double exchangePercent;
	
	/**
	 * 成交
	 */
	private Long dealCnt;
	
	private Date createTime;
	
	private Timestamp lastUpdateTime;

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}

	public Double getIncreasePercent() {
		return increasePercent;
	}

	public void setIncreasePercent(Double increasePercent) {
		this.increasePercent = increasePercent;
	}

	public Double getWavePercent() {
		return wavePercent;
	}

	public void setWavePercent(Double wavePercent) {
		this.wavePercent = wavePercent;
	}

	public Long getAllCnt() {
		return allCnt;
	}

	public void setAllCnt(Long allCnt) {
		this.allCnt = allCnt;
	}

	public Double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}

	public Double getExchangePercent() {
		return exchangePercent;
	}

	public void setExchangePercent(Double exchangePercent) {
		this.exchangePercent = exchangePercent;
	}

	public Long getDealCnt() {
		return dealCnt;
	}

	public void setDealCnt(Long dealCnt) {
		this.dealCnt = dealCnt;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public static boolean isIncreaseToCeil(StockBaseInfo stockBaseInfo){
		return DoubleCompareUtil.gt(stockBaseInfo.getIncreasePercent(), StockAnalyseConstants.INCEASE_TO_CEIL_PERCENT_LIMIT);
	}
}
