package com.qingqing.stock_analyse.service.analyse;

import java.util.Date;
import java.util.Map;

import com.qingqing.stock_analyse.domain.result.StockPulseResult;

public interface PulseAnalyseService {

	/**
	 * ��ѯ����ָ�����ڵķ��������͵Ĺ�Ʊ��Ϣ
	 */
	Map<String, StockPulseResult> findAllPulseResult(Date date);
	
	/**
	 * ����ָ�����ڵ����й�Ʊ����ѡ�����з��������͵Ĺ�Ʊ
	 */
	Map<String, StockPulseResult> analysePulseResult(Date date);
	
	/**
	 * ����ָ�����ڣ�ָ�������ҷ��������͵Ĺ�Ʊ����ѯ����ͣ���ԣ���Ϊ��ͣ���򷵻�null
	 */
	StockPulseResult analysePulseResult(Date date, String stockCode);
}
