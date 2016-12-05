package com.qingqing.stock_analyse.controller;

import com.qingqing.stock_analyse.controller.converter.StockResultViewConverter;
import com.qingqing.stock_analyse.domain.result.StockPulseResult;
import com.qingqing.stock_analyse.domain.result.StockTZiTypeResult;
import com.qingqing.stock_analyse.domain.result.StockTiaoKongResult;
import com.qingqing.stock_analyse.domain.result.StockYiZiBanResult;
import com.qingqing.stock_analyse.service.StockCodeService;
import com.qingqing.stock_analyse.service.analyse.PulseAnalyseService;
import com.qingqing.stock_analyse.service.analyse.TZiTypeAnalyseService;
import com.qingqing.stock_analyse.service.analyse.TiaoKongAnalyseService;
import com.qingqing.stock_analyse.service.analyse.YiZiBanAnalyseService;
import com.qingqing.stock_analyse.util.StockDateUtil;
import com.qingqing.stock_analyse.util.web.RequestExtract;
import com.qingqing.stock_analyse.util.web.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by xuya on 2016/11/30.
 */
@Controller
@RequestMapping("/stock_info/view")
public class StockInfoViewController {

    @Autowired
    private StockCodeService stockCodeService;
    @Autowired
    private PulseAnalyseService pulseAnalyseService;
    @Autowired
    private YiZiBanAnalyseService yiZiBanAnalyseService;
    @Autowired
    private TZiTypeAnalyseService tZiTypeAnalyseService;
    @Autowired
    private TiaoKongAnalyseService tiaoKongAnalyseService;


    @RequestMapping("analyse_result/test")
    public void testShowAnalyseResult(HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        ServletUtil.sendTextResponse(response, HttpStatus.OK.value(), "中文呢， ABCEEDAas");
    }

    @RequestMapping("analyse_result/tzi_type")
    public void showTZiTypeAnalyseResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Date date = RequestExtract.getDate(httpServletRequest);
        Map<String, StockTZiTypeResult> map = tZiTypeAnalyseService.findAllTZiTypeResult(date);
        Map<String, String> stockCodeToNameMap = stockCodeService.findAllStockMapping();
        ServletUtil.sendTextResponse(httpServletResponse, HttpStatus.OK.value(), StockResultViewConverter.tZiTypeResultMapToString(map, stockCodeToNameMap, date));
    }

    @RequestMapping("analyse_result/yi_zi_ban")
    public void showYiZiBanAnalyseResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Date date = RequestExtract.getDate(httpServletRequest);
        Map<String, StockYiZiBanResult> map = yiZiBanAnalyseService.findAllYiZiBanResult(date);
        Map<String, String> stockCodeToNameMap = stockCodeService.findAllStockMapping();
        ServletUtil.sendTextResponse(httpServletResponse, HttpStatus.OK.value(), StockResultViewConverter.yiZiBanResultMapToString(map, stockCodeToNameMap, date));
    }

    @RequestMapping("analyse_result/tiaokong")
    public void showTiaoKongAnalyseResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Date date = RequestExtract.getDate(httpServletRequest);
        Map<String, StockTiaoKongResult> map = tiaoKongAnalyseService.findAllTiaoKongResult(date);
        Map<String, String> stockCodeToNameMap = stockCodeService.findAllStockMapping();
        ServletUtil.sendTextResponse(httpServletResponse, HttpStatus.OK.value(), StockResultViewConverter.tiaoKongResultMapToString(map, stockCodeToNameMap, date));
    }

    @RequestMapping("analyse_result/pulse")
    public void showPulseAnalyseResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Date date = RequestExtract.getDate(httpServletRequest);
        Map<String, StockPulseResult> map = pulseAnalyseService.findAllPulseResult(date);
        Map<String, String> stockCodeToNameMap = stockCodeService.findAllStockMapping();
        ServletUtil.sendTextResponse(httpServletResponse, HttpStatus.OK.value(), StockResultViewConverter.pulseResultMapToString(map, stockCodeToNameMap, date));
    }

    @RequestMapping("analyse_result/summary")
    public void showAnalyseSummaryResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        Date date = RequestExtract.getDate(httpServletRequest);
        Map<String, StockPulseResult> pulseMap = pulseAnalyseService.findAllPulseResult(date);
        Map<String, StockTiaoKongResult> tiaoKongMap = tiaoKongAnalyseService.findAllTiaoKongResult(date);
        Map<String, StockYiZiBanResult> yiziBanMap = yiZiBanAnalyseService.findAllYiZiBanResult(date);
        Map<String, StockTZiTypeResult> tZiTypeMap = tZiTypeAnalyseService.findAllTZiTypeResult(date);
        Map<String, String> stockCodeToNameMap = stockCodeService.findAllStockMapping();



        ServletUtil.sendTextResponse(httpServletResponse, HttpStatus.OK.value(), StockResultViewConverter.resultMapsToString(pulseMap, tiaoKongMap, yiziBanMap, tZiTypeMap, stockCodeToNameMap, date));
    }
}
