package com.qingqing.stock_analyse.dao.analyse;

import com.qingqing.stock_analyse.domain.result.StockYiZiBanResult;

import java.util.Date;
import java.util.List;

public interface StockYiZiBanResultMapper {

    int insertIgnore(StockYiZiBanResult record);

    StockYiZiBanResult selectByPrimaryKey(Integer id);

    List<StockYiZiBanResult> findAllByDate(Date date);
}