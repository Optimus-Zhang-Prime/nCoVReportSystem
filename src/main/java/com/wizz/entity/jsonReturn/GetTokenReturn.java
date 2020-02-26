package com.wizz.entity.jsonReturn;

/**
 * @descrip：获取accesstoken的返回值封装
 * @author: 李佳
 * @create： 2020-02-26-20:56
 **/
public class GetTokenReturn {
    private String access_token;
    private String expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
