package com.qingqing.stock_analyse.service.analyse;

import java.util.Date;
import java.util.Map;

import com.qingqing.stock_analyse.domain.result.StockTiaoKongResult;

public interface TiaoKongAnalyseService {

	/**
	 * ��ѯ����ָ�����ڵ����չ�Ʊ��¼
	 */
	Map<String, StockTiaoKongResult> findAllTiaoKongResult(Date date);
	
	/**
	 * ����ָ�����ڵ����й�Ʊ����ѡ��������ͣ�Ĺ�Ʊ
	 */
	Map<String, StockTiaoKongResult> analyseTiaoKongResult(Date date);
	
	/**
	 * ����ָ�����ڣ�ָ������Ĺ�Ʊ����ѯ����ͣ���ԣ���Ϊ��ͣ���򷵻�null
	 */
	StockTiaoKongResult analyseTiaoKongResult(Date date, String stockCode);
}
