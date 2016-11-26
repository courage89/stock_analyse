package com.qingqing.stock_analyse.service;

import java.util.List;
import java.util.Map;

public interface StockCodeService {

	Map<String, String> findAllStockMapping();
	
	List<String> findAllStockCodes();
}
