package com.wizz.entity.jsonReturn;

/**
 * @descrip：delete返回值封装
 * @author: 李佳
 * @create： 2020-03-02-14:35
 **/
public class DeleteReturn {
    private String errcode;
    private String errmsg;
    private String deleted;

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

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public DeleteReturn() {
    }

    public DeleteReturn(String errcode, String errmsg, String deleted) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.deleted = deleted;
    }
}
