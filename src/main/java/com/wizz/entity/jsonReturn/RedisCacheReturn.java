package com.wizz.entity.jsonReturn;

/**
 * @descrip：redis返回值封装
 * @author: 李佳
 * @create： 2020-03-08-09:07
 **/
public class RedisCacheReturn {
    private String access_token;
    private String ttl;

    @Override
    public String toString() {
        return "RedisCacheReturn{" +
                "access_token='" + access_token + '\'' +
                ", ttl='" + ttl + '\'' +
                '}';
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public RedisCacheReturn() {
    }

    public RedisCacheReturn(String access_token, String ttl) {
        this.access_token = access_token;
        this.ttl = ttl;
    }
}
