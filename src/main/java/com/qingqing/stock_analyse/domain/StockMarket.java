package com.qingqing.stock_analyse.domain;

/**
 * Created by xuya on 2016/11/27.
 */
public enum StockMarket {

    ShangHai(1, 1164),
    ShenZhen(2, 1880),
    HongKong(3, 200),
    USA(4, 300);

    private int value;

    private int count;

    public int getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    private StockMarket(int value, int count) {
        this.value = value;
        this.count = count;
    }
}
