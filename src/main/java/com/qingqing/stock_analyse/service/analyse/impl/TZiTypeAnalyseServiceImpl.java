package com.qingqing.stock_analyse.service.analyse.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.qingqing.stock_analyse.dao.analyse.TZiTypeAnalyseMapper;
import com.qingqing.stock_analyse.domain.result.StockTZiTypeResult;
import com.qingqing.stock_analyse.service.StockBaseInfoService;
import com.qingqing.stock_analyse.service.StockCodeService;
import com.qingqing.stock_analyse.service.analyse.TZiTypeAnalyseService;

public class TZiTypeAnalyseServiceImpl implements TZiTypeAnalyseService {

	@Autowired
	private StockCodeService stockCodeService;
	@Autowired
	private StockBaseInfoService stockBaseInfoService;
	@Autowired
	private TZiTypeAnalyseMapper tZiTypeAnalyseMapper;
	
	@Override
	public Map<String, StockTZiTypeResult> findAllTZiTypeResult(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, StockTZiTypeResult> analyseTZiTypeResult(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockTZiTypeResult analyseTZiTypeResult(Date date, String stockCode) {
		// TODO Auto-generated method stub
		return null;
	}
}