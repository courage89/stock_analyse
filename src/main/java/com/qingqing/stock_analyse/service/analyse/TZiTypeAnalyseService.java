package com.qingqing.stock_analyse.service.analyse;

import java.util.Date;
import java.util.Map;

import com.qingqing.stock_analyse.domain.result.StockTZiTypeResult;

public interface TZiTypeAnalyseService {

	/**
	 * ��ѯ����ָ�����ڵķ���T���͵Ĺ�Ʊ��Ϣ
	 */
	Map<String, StockTZiTypeResult> findAllTZiTypeResult(Date date);
	
	/**
	 * ����ָ�����ڵ����й�Ʊ����ѡ�����з���T���͵Ĺ�Ʊ
	 */
	Map<String, StockTZiTypeResult> analyseTZiTypeResult(Date date);
	
	/**
	 * ����ָ�����ڣ�ָ�������ҷ���T���͵Ĺ�Ʊ����ѯ����ͣ���ԣ���Ϊ��ͣ���򷵻�null
	 */
	StockTZiTypeResult analyseTZiTypeResult(Date date, String stockCode);
}
