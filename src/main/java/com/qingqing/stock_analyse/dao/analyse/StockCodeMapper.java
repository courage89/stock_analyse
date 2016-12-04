package com.qingqing.stock_analyse.dao.analyse;

import com.qingqing.stock_analyse.domain.StockCode;
import java.util.List;

public interface StockCodeMapper {

    int insert(StockCode record);

    StockCode selectByPrimaryKey(Integer id);

    List<StockCode> selectAll();
}