package com.wizz.entity.jsonReturn;

/**
 * @descrip：云函数调用返回值
 * @author: 李佳
 * @create： 2020-03-02-22:52
 **/
public class CloudFunctionReturn {
    private String errcode;
    private String errmsg;
    private String resp_data;

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

    public String getResp_data() {
        return resp_data;
    }

    public void setResp_data(String resp_data) {
        this.resp_data = resp_data;
    }

    public CloudFunctionReturn() {
    }

    public CloudFunctionReturn(String errcode, String errmsg, String resp_data) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.resp_data = resp_data;
    }
}
