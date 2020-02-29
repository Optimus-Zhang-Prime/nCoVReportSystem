package com.wizz.entity.jsonReturn;

/**
 * @descrip：update操作的返回值封装
 * @author: 李佳
 * @create： 2020-02-29-01:19
 **/
public class UpdateReturn {
    private String errcode;
    private String errmsg;
    private String matched;
    private String modified;
    private String id;

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

    public String getMatched() {
        return matched;
    }

    public void setMatched(String matched) {
        this.matched = matched;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UpdateReturn() {
    }

    public UpdateReturn(String errcode, String errmsg, String matched, String modified, String id) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.matched = matched;
        this.modified = modified;
        this.id = id;
    }
}
