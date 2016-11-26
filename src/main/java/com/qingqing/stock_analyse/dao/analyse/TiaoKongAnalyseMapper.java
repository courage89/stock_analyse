package com.qingqing.stock_analyse.dao.analyse;

import java.util.Date;
import java.util.List;

import com.qingqing.stock_analyse.domain.result.StockTiaoKongResult;

public interface TiaoKongAnalyseMapper {

	public void insert(StockTiaoKongResult result);

	public List<StockTiaoKongResult> findAllByDate(Date date);

}
