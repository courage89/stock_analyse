package com.qingqing.stock_analyse.domain;

/**
 * Created by xuya on 2016/11/27.
 */
public enum StockMarket {

    ShangHai(1),
    ShenZhen(2),
    HongKong(3),
    USA(4);

    private int value;

    public int getValue() {
        return value;
    }

    private StockMarket(int value) {
        this.value = value;
    }
}
