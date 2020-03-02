package com.wizz.utils;

import com.alibaba.fastjson.JSON;
import com.wizz.entity.jsonReturn.AddReturn;
import com.wizz.entity.jsonReturn.QueryReturn;
import com.wizz.entity.jsonReturn.UpdateReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.property.DataBaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @descrip：数据库查询的工具类
 * @author: 李佳
 * @create： 2020-02-28-16:31
 **/
@Component
public class DataBaseUtils {
    @Autowired
    private DataBaseProperties dataBaseProperties;
    @Autowired
    private TokenUtils tokenUtils;
    private RestTemplate restTemplate = new RestTemplate();
    // 查
    public QueryReturn getQueryResult (String query,Object ... args) {
        // 获取必须的access_token
        String access_token = tokenUtils.getAccessToken();
        String queryString = dataBaseProperties.getDatabaseQuery()  + access_token;
        Map<String,Object> map = dataBaseProperties.getDbBody();
        // 组织post body
        map.put("query",String.format(query,args));
//        System.out.println(map);
        // json返回值
        String rawOutput = restTemplate.postForObject(queryString,map,String.class);
//        System.out.println(rawOutput);
        // json对象映射
        QueryReturn queryReturn = JSON.parseObject(rawOutput, QueryReturn.class);
        return queryReturn;
    }
    //增
    public void addData (String query, Object ... args) {
        String access_token = tokenUtils.getAccessToken();
        String queryString = dataBaseProperties.getDatabaseAdd()  + access_token;
        Map<String,Object> map = dataBaseProperties.getDbBody();
        map.put("query",String.format(query,args));
        // json返回值
        String rawOutput = restTemplate.postForObject(queryString,map,String.class);
        // json对象映射
        AddReturn addReturn = JSON.parseObject(rawOutput, AddReturn.class);
        // 获得errcode
        String errcode = addReturn.getErrcode();
        // 这里实际上可以使用注解进行校验  参考codesheep
        if (!"0".equals(errcode)) {
            throw new DbErrorException(addReturn.getErrmsg());
        }
    }
    // 改
    public void updateData (String query,Object ...args) {
        // 获取必须的access_token
        String access_token = tokenUtils.getAccessToken();
        String queryString = dataBaseProperties.getDatabaseUpdate()  + access_token;
        Map<String,Object> map = dataBaseProperties.getDbBody();
        // 组织post body
        map.put("query",String.format(query,args));
        // json返回值
        String rawOutput = restTemplate.postForObject(queryString,map,String.class);
        // json对象映射
        UpdateReturn updateReturn = JSON.parseObject(rawOutput, UpdateReturn.class);
        // 获得errcode
        String errcode = updateReturn.getErrcode();
        String errmsg = updateReturn.getErrmsg();
        if (!"0".equals(errcode)) {
            throw new DbErrorException(errmsg);
        }
    }
}
