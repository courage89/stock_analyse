package com.qingqing.stock_analyse.dao.analyse;

import com.qingqing.stock_analyse.domain.StockInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StockInfoMapper {

    void insert(StockInfo stockInfo);

    StockInfo selectByPrimaryKey(@Param("id") Long id);

    void batchInsert(@Param("stockInfos") List<StockInfo> stockInfos);

    StockInfo findByStockCodeAndDate(@Param("stockCode")String stockCode, @Param("stockDate")Date stockDate);

    List<StockInfo> findByStockCodeAndDateRange(@Param("stockCode") String stockCode, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}