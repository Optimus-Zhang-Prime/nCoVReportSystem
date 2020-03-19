package com.wizz.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @descrip：redis缓存获取access_token的配置
 * @author: 李佳
 * @create： 2020-03-08-08:41
 **/
@ConfigurationProperties(prefix = "token.cache")
@Service
public class RedisCacheProperties {
    private String url;
    private Map<String,Object> params;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
