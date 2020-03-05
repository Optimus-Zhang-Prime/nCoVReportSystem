package com.wizz.entity.jsonReturn;

/**
 * @descrip：数据库count返回值封装
 * @author: 李佳
 * @create： 2020-03-05-14:28
 **/
public class CountReturn {
    private String errcode;
    private String errmsg;
    private Integer count;

    @Override
    public String toString() {
        return "CountReturn{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", count='" + count + '\'' +
                '}';
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public CountReturn() {
    }

    public CountReturn(String errcode, String errmsg, Integer count) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.count = count;
    }
}
