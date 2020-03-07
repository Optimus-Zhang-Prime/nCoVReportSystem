package com.wizz.entity;

/**
 * @descrip：验证码映射
 * @author: 李佳
 * @create： 2020-03-07-22:26
 **/
public class Vcode {
    private String tel;
    private String code;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Vcode() {
    }

    public Vcode(String tel, String code) {
        this.tel = tel;
        this.code = code;
    }
}
