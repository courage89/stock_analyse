package com.qingqing.stock_analyse.dao.analyse;

import com.qingqing.stock_analyse.domain.result.StockPulseResult;

import java.util.Date;
import java.util.List;

public interface StockPulseResultMapper {
    int insertIgnore(StockPulseResult record);

    StockPulseResult selectByPrimaryKey(Long id);

    List<StockPulseResult> findAllByDate(Date date);
}