package com.qingqing.stock_analyse.controller.converter;

import com.qingqing.stock_analyse.domain.result.StockTZiTypeResult;
import com.qingqing.stock_analyse.domain.result.StockYiZiBanResult;
import com.qingqing.stock_analyse.util.StockDateUtil;

import java.util.Date;
import java.util.Map;

/**
 * Created by xuya on 2016/12/4.
 */
public class StockResultViewConverter {

    public final static String yiZiBanResultMapToString(Map<String, StockYiZiBanResult> map, Map<String, String> stockCodeToNameMap, Date date) {
        StringBuilder sb = new StringBuilder();
        StringBuilder contentSb = new StringBuilder();
        for (Map.Entry<String, StockYiZiBanResult> entry : map.entrySet()) {
            contentSb.append("股票代码: ").append(entry.getKey()).append("\t")
                    .append("股票名称: ").append(stockCodeToNameMap.get(entry.getKey())).append("\t")
                    .append("当日价格: ").append(entry.getValue().getPrice()).append("\t")
                    .append("\r\n");
        }

        sb.append("一字板股票分析结果如下\r\n")
                .append("日期： ").append(StockDateUtil.dateToString(date)).append("\r\n")
                .append("所有满足一字板的股票详情:\r\n").append(contentSb).append("\r\n").append("\r\n").append("\r\n");
        return sb.toString();
    }

    public final static String tZiTypeResultMapToString(Map<String, StockTZiTypeResult> map, Map<String, String> stockCodeToNameMap, Date date) {

        StringBuilder sb = new StringBuilder();
        StringBuilder contentSb = new StringBuilder();
        for (Map.Entry<String, StockTZiTypeResult> entry : map.entrySet()) {
            contentSb.append("股票代码: ").append(entry.getKey()).append("\t")
                    .append("股票名称: ").append(stockCodeToNameMap.get(entry.getKey())).append("\t")
                    .append("当日最低价: ").append(entry.getValue().getCurrentMin()).append("\t")
                    .append("当日最高价: ").append(entry.getValue().getCurrentMax()).append("\t")
                    .append("前一日最高价: ").append(entry.getValue().getPrevMax()).append("\t")
                    .append("当日最低价相对前一日最高价的涨幅: ").append(entry.getValue().getMinIncrPercent()).append("\t")
                    .append("\r\n");
        }

        sb.append("T字形股票分析结果如下\r\n")
                .append("日期： ").append(StockDateUtil.dateToString(date)).append("\r\n")
                .append("所有满足T字形的股票详情:\r\n").append(contentSb).append("\r\n").append("\r\n").append("\r\n");
        return sb.toString();
    }
}
