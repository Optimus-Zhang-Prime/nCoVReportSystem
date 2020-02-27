package com.wizz.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @descrip：ids相关的设置
 * @author: 李佳
 * @create： 2020-02-27-19:04
 **/
@ConfigurationProperties(prefix = "ids")
@Service
public class IdsProperties {
    private String idsAccessToken;
    private String getIdsData;
    private String getIdsTokenParams;

    public String getGetIdsData() {
        return getIdsData;
    }

    public void setGetIdsData(String getIdsData) {
        this.getIdsData = getIdsData;
    }

    public String getIdsAccessToken() {
        return idsAccessToken;
    }

    public void setIdsAccessToken(String idsAccessToken) {
        this.idsAccessToken = idsAccessToken;
    }

    public String getGetIdsTokenParams() {
        return getIdsTokenParams;
    }

    public void setGetIdsTokenParams(String getIdsTokenParams) {
        this.getIdsTokenParams = getIdsTokenParams;
    }
}
