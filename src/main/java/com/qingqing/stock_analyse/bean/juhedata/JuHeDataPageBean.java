package com.qingqing.stock_analyse.bean.juhedata;

import com.qingqing.common.util.JsonUtil;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by xuya on 2016/11/27.
 */
public class JuHeDataPageBean<T> {

    private String totalCount;

    private String page;

    private String num;

    private List<T> data;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static JuHeDataPageBean parserFromJsonString(String jsonString, Class X) {
        JuHeDataPageBean pageBean = new JuHeDataPageBean();
        JSONObject pageBeanObject = JSONObject.fromObject(jsonString);
        pageBean.setNum(pageBeanObject.getString("num"));
        pageBean.setPage(pageBeanObject.getString("page"));
        pageBean.setTotalCount(pageBeanObject.getString("totalCount"));
        pageBean.setData(JsonUtil.parserJsonList(pageBeanObject.getString("data"), X));
        return pageBean;
    }
}
