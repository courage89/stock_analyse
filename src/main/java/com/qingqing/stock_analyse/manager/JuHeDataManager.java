package com.qingqing.stock_analyse.manager;

/**
 * Created by xuya on 2016/11/27.
 */


import com.qingqing.common.exception.QingQingRuntimeException;
import com.qingqing.stock_analyse.bean.juhedata.JuHeDataPageBean;
import com.qingqing.stock_analyse.bean.juhedata.JuHeDataStockInfoBean;
import com.qingqing.stock_analyse.domain.StockMarket;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 股票数据调用示例代码 － 聚合数据
 * 在线接口文档：http://www.juhe.cn/docs/21
 **/

public class JuHeDataManager {
    public final String DEF_CHATSET = "UTF-8";
    public final int DEF_CONN_TIMEOUT = 30000;
    public final int DEF_READ_TIMEOUT = 30000;
    public final Logger logger = LoggerFactory.getLogger(JuHeDataManager.class);
    public final String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的KEY
    private String appKey;

    private static final String getStockDetailUrl(StockMarket stockMarket) {
        switch (stockMarket) {
            case ShangHai:
            case ShenZhen:
                return "http://web.juhe.cn:8080/finance/stock/hs";
            case HongKong:
                return "http://web.juhe.cn:8080/finance/stock/hk";
            case USA:
                return "http://web.juhe.cn:8080/finance/stock/usa";
            default:
                throw new QingQingRuntimeException("unknown stocktMarket:" + stockMarket);
        }
    }

    private static final String getStockListUrl(StockMarket stockMarket) {
        switch (stockMarket) {
            case ShangHai:
                return "http://web.juhe.cn:8080/finance/stock/shall";
            case ShenZhen:
                return "http://web.juhe.cn:8080/finance/stock/szall";
            case HongKong:
                return "http://web.juhe.cn:8080/finance/stock/hkall";
            case USA:
                return "http://web.juhe.cn:8080/finance/stock/usaall";
            default:
                throw new QingQingRuntimeException("unknown stocktMarket:" + stockMarket);
        }
    }

    public void getStockDetailForHS(StockMarket stockMarket) {
        String result = null;
        Map params = new HashMap();//请求参数
        params.put("gid", "");//股票编号，上海股市以sh开头，深圳股市以sz开头如：sh601009
        params.put("key", appKey);//APP Key

        try {
            result = net(getStockDetailUrl(stockMarket), params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2.香港股市
    public void getStockDetailForHongKong() {
        String result = null;
        String url = "http://web.juhe.cn:8080/finance/stock/hk";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("num", "");//股票代码，如：00001 为“长江实业”股票代码
        params.put("key", appKey);//APP Key

        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //3.美国股市
    public void getStockDetailForUSA() {
        String result = null;
        String url = "http://web.juhe.cn:8080/finance/stock/usa";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("gid", "");//股票代码，如：aapl 为“苹果公司”的股票代码
        params.put("key", appKey);//APP Key

        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JuHeDataPageBean<JuHeDataStockInfoBean> getStockeInfoBean(int pageNo, StockMarket stockMarket) {
        String result = null;
        String url = "http://web.juhe.cn:8080/finance/stock/shall";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", appKey);//您申请的APPKEY
        params.put("page", "" + pageNo);//第几页,每页20条数据,默认第1页
        try {
            result = net(getStockListUrl(stockMarket), params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                String jsonString = object.getJSONObject("result").toString();
                logger.debug("getStockeInfoBean pageNo:{}, result:{}", pageNo, jsonString);
                return JuHeDataPageBean.parserFromJsonString(jsonString, JuHeDataStockInfoBean.class);
            } else {
                throw new QingQingRuntimeException("getStockeInfoBean error, stockMarket" + stockMarket + ", pageNo:" + pageNo + ", errorCode:" + object.get("error_code") + ", errorMsg:" + object.get("reason"));
            }
        } catch (Exception e) {
            throw new QingQingRuntimeException("getStockeInfoBean fail, stockMarket" + stockMarket + ", pageNo:" + pageNo, e);
        }
    }

    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    private String net(String strUrl, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}