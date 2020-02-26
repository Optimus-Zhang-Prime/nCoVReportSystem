package com.wizz.entity.jsonReturn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @descrip：查询语句的返回模型
 * @author: 李佳
 * @create： 2020-02-26-21:18
 **/
public class QueryReturn {
    private String errcode;
    private String errmsg;
    private Map<String, Object> pager = new HashMap<>();
    private List<String> data;

    public QueryReturn() {
    }

    public QueryReturn(String errcode, String errmsg, Map<String, Object> pager, List<String> data) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.pager = pager;
        this.data = data;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Map<String, Object> getPager() {
        return pager;
    }

    public void setPager(Map<String, Object> pager) {
        this.pager = pager;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
