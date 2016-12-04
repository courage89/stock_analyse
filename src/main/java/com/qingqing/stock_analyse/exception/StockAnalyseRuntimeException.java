package com.qingqing.stock_analyse.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by xuya on 2016/12/4.
 */
public class StockAnalyseRuntimeException extends NestedRuntimeException {

    public StockAnalyseRuntimeException(String msg) {
        super(msg);
    }

    public StockAnalyseRuntimeException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
    }

    public StockAnalyseRuntimeException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
