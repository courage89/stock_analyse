package com.qingqing.stock_analyse.dao.analyse;

import com.qingqing.stock_analyse.domain.result.StockPulseResult;
import com.qingqing.stock_analyse.domain.result.StockTiaoKongResult;

import java.util.Date;
import java.util.List;

public interface StockTiaoKongResultMapper {
    int insertIgnore(StockTiaoKongResult record);

    StockTiaoKongResult selectByPrimaryKey(Integer id);

    List<StockTiaoKongResult> findAllByDate(Date date);
}