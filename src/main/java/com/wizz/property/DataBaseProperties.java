package com.wizz.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @descrip：数据库的配置
 * @author: 李佳
 * @create： 2020-02-26-02:36
 **/

@ConfigurationProperties(prefix = "db")
@Service
public class DataBaseProperties {
    private String accessToken;
    private Map<String,Object> dbBody;
    private Map<String,Object> getTokenParams;
    private String databaseQuery;
    private String databaseAdd;
    private String cloudFunction;

    public String getCloudFunction() {
        return cloudFunction;
    }

    public void setCloudFunction(String cloudFunction) {
        this.cloudFunction = cloudFunction;
    }

    public String getDatabaseDelete() {
        return databaseDelete;
    }

    public void setDatabaseDelete(String databaseDelete) {
        this.databaseDelete = databaseDelete;
    }

    private String databaseDelete;

    public String getDatabaseUpdate() {
        return databaseUpdate;
    }

    public void setDatabaseUpdate(String databaseUpdate) {
        this.databaseUpdate = databaseUpdate;
    }

    private String databaseUpdate;
    public String getDatabaseAdd() {
        return databaseAdd;
    }

    public void setDatabaseAdd(String databaseAdd) {
        this.databaseAdd = databaseAdd;
    }



    public String getDatabaseQuery() {
        return databaseQuery;
    }

    public void setDatabaseQuery(String databaseQuery) {
        this.databaseQuery = databaseQuery;
    }


    public Map<String, Object> getGetTokenParams() {
        return getTokenParams;
    }

    public void setGetTokenParams(Map<String, Object> getTokenParams) {
        this.getTokenParams = getTokenParams;
    }

    public Map<String, Object> getDbBody() {
        return dbBody;
    }

    public void setDbBody(Map<String, Object> dbBody) {
        this.dbBody = dbBody;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
