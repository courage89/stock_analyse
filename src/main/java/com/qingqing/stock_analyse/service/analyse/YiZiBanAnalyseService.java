package com.qingqing.stock_analyse.service.analyse;

import java.util.Date;
import java.util.Map;

import com.qingqing.stock_analyse.domain.result.StockYiZiBanResult;

public interface YiZiBanAnalyseService {

	/**
	 * ��ѯ����ָ�����ڵĹ�Ʊ��ͣ��¼
	 */
	Map<String, StockYiZiBanResult> findAllYiZiBanResult(Date date);
	
	/**
	 * ����ָ�����ڵ����й�Ʊ����ѡ��������ͣ�Ĺ�Ʊ
	 */
	Map<String, StockYiZiBanResult> analyseYiZiBanResult(Date date);
	
	/**
	 * ����ָ�����ڣ�ָ������Ĺ�Ʊ����ѯ����ͣ���ԣ���Ϊ��ͣ���򷵻�null
	 */
	StockYiZiBanResult analyseYiZiBanResult(Date date, String stockCode);
}
