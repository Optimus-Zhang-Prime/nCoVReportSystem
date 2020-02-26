package com.wizz.dao.impl;

import com.alibaba.fastjson.JSON;
import com.wizz.dao.UserDao;
import com.wizz.entity.User;
import com.wizz.entity.jsonReturn.AddReturn;
import com.wizz.entity.jsonReturn.QueryReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.property.DataBaseProperties;
import com.wizz.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @descrip：UserDao实现
 * @author: 李佳
 * @create： 2020-02-26-02:31
 **/
@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private DataBaseProperties dataBaseProperties;
    @Autowired
    private TokenUtils tokenUtils;
    RestTemplate restTemplate = new RestTemplate();
    @Override
    public String getUserByName(String name) {
        // 获取必须的access_token
        String access_token = tokenUtils.getAccessToken();
        String queryString = dataBaseProperties.getDatabaseQuery()  + access_token;
        Map<String,Object> map = dataBaseProperties.getDbBody();
        // 组织post body
        map.put("query",String.format("db.collection('user').where({username: '%s'}).get()",name));
        // json返回值
        String rawOutput = restTemplate.postForObject(queryString,map,String.class);
        // json对象映射
        QueryReturn queryReturn = JSON.parseObject(rawOutput, QueryReturn.class);
        // 获得data字段
        List<String> strOutput = queryReturn.getData();
        // 获得errcode
        String errcode = queryReturn.getErrcode();
        // 这里实际上可以使用注解进行校验  参考codesheep
//        System.out.println(rawOutput);
        if (!"0".equals(errcode)) {
            throw new DbErrorException("");
        } else if (strOutput.isEmpty()) {
            throw new DbErrorException("查无此人");
        }
        // 获得唯一的用户信息 json
        String output = strOutput.get(0);
        return output;
    }

    @Override
    public void addUser(User user) {
        String access_token = tokenUtils.getAccessToken();
        String queryString = dataBaseProperties.getDatabaseAdd()  + access_token;
        Map<String,Object> map = dataBaseProperties.getDbBody();
        map.put("query",String.format("db.collection('user').add({data:[{username: '%s', password: '%s'}]})",user.getUsername(),user.getPassword()));
        // json返回值
        String rawOutput = restTemplate.postForObject(queryString,map,String.class);
        // json对象映射
        AddReturn addReturn = JSON.parseObject(rawOutput, AddReturn.class);
        // 获得errcode
        String errcode = addReturn.getErrcode();
        // 这里实际上可以使用注解进行校验  参考codesheep
//        System.out.println(rawOutput);
        if (!"0".equals(errcode)) {
            throw new DbErrorException(addReturn.getErrmsg());
        }
    }
}