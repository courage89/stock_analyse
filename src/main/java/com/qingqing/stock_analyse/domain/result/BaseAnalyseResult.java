package com.qingqing.stock_analyse.domain.result;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 基本分析结果对象，用于后续统计
 */
public class BaseAnalyseResult {

	private Long id;

	private String stockCode;
	
	private Date date;
	
	private Date createTime;
	
	private Timestamp lastUpdateTime;
	
	private Double nextDayIncreasePercent;
	
	private Double nextWeekIncreasePercent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Double getNextDayIncreasePercent() {
		return nextDayIncreasePercent;
	}

	public void setNextDayIncreasePercent(Double nextDayIncreasePercent) {
		this.nextDayIncreasePercent = nextDayIncreasePercent;
	}

	public Double getNextWeekIncreasePercent() {
		return nextWeekIncreasePercent;
	}

	public void setNextWeekIncreasePercent(Double nextWeekIncreasePercent) {
		this.nextWeekIncreasePercent = nextWeekIncreasePercent;
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
}
