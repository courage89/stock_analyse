package com.qingqing.stock_analyse.dao.analyse;

import com.qingqing.stock_analyse.domain.result.StockTZiTypeResult;
import com.qingqing.stock_analyse.domain.result.StockTiaoKongResult;

import java.util.Date;
import java.util.List;

public interface StockTZiTypeResultMapper {
    int insertIgnore(StockTZiTypeResult record);

    StockTZiTypeResult selectByPrimaryKey(Integer id);

    List<StockTZiTypeResult> findAllByDate(Date date);

}