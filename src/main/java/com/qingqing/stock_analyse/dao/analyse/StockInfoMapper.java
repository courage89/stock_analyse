package com.qingqing.stock_analyse.dao.analyse;

import com.qingqing.stock_analyse.domain.StockInfo;
import java.util.List;

public interface StockInfoMapper {

    int insert(StockInfo record);

    StockInfo selectByPrimaryKey(Integer id);
}