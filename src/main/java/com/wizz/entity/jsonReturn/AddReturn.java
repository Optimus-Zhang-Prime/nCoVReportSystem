package com.wizz.entity.jsonReturn;

import java.util.List;

/**
 * @descrip：插入数据的返回值疯转
 * @author: 李佳
 * @create： 2020-02-27-00:30
 **/
public class AddReturn {
    private String errcode;
    private String errmsg;
    private List<String> id_list;

    public AddReturn() {
    }

    public AddReturn(String errcode, String errmsg, List<String> id_list) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.id_list = id_list;
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

    public List<String> getId_list() {
        return id_list;
    }

    public void setId_list(List<String> id_list) {
        this.id_list = id_list;
    }
}
