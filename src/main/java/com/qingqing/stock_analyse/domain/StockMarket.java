package com.qingqing.stock_analyse.domain;

import com.qingqing.common.intf.HasValueInterface;

/**
 * Created by xuya on 2016/11/27.
 */
public enum StockMarket implements HasValueInterface{

    ShangHai(1, 1164, "sh"),
    ShenZhen(2, 1880, "sz"),
    HongKong(3, 200, "hk"),
    USA(4, 300, "usa");

    private int value;

    private int count;

    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    public Integer getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    private StockMarket(int value, int count, String prefix) {
        this.value = value;
        this.count = count;
        this.prefix = prefix;
    }
}
