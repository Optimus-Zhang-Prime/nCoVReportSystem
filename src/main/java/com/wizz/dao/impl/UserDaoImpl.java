package com.wizz.dao.impl;

import com.alibaba.fastjson.JSON;
import com.wizz.dao.UserDao;
import com.wizz.entity.User;
import com.wizz.entity.jsonReturn.QueryReturn;
import com.wizz.entity.jsonReturn.UpdateReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.property.DataBaseProperties;
import com.wizz.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @descrip：UserDao实现
 * @author: 李佳
 * @create： 2020-02-26-02:31
 **/
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private DataBaseProperties dataBaseProperties;
    @Autowired
    private TokenUtils tokenUtils;
    RestTemplate restTemplate = new RestTemplate();
    @Override
    public User getUserById(String id) {
        // 获取必须的access_token
        String access_token = tokenUtils.getAccessToken();
        String queryString = dataBaseProperties.getDatabaseQuery()  + access_token;
        Map<String,Object> map = dataBaseProperties.getDbBody();
        // 组织post body
        map.put("query",String.format("db.collection('user-1').limit(100).where({_openid: '%s'}).get()",id));
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
            throw new DbErrorException(errcode);
        } else if (strOutput.isEmpty()) {
            throw new DbErrorException("查无此人");
        }
        // 获得唯一的用户信息 json
        String output = strOutput.get(0);
        User user = JSON.parseObject(output,User.class);
        return user;
    }
//    @Update({"update User set index=91 where id=#{id}"})
    @Override
    public void setUserIndex90(String id) {//改成设为91了
        // 获取必须的access_token
        String access_token = tokenUtils.getAccessToken();
        String queryString = dataBaseProperties.getDatabaseUpdate()  + access_token;
        Map<String,Object> map = dataBaseProperties.getDbBody();
        // 组织post body
        map.put("query",String.format("db.collection('user-1').where({_openid: '%s'}).update({data:{index: 91}})",id));
        // json返回值
        String rawOutput = restTemplate.postForObject(queryString,map,String.class);
        // json对象映射
        UpdateReturn updateReturn = JSON.parseObject(rawOutput, UpdateReturn.class);
        // 获得errcode
        String errcode = updateReturn.getErrcode();
        if (!"0".equals(errcode)) {
            throw new DbErrorException(errcode);
        }
    }
//    @Update({"update User set index=50 where id=#{id}"})
    @Override
    public void setUserIndex50(String id) {
        // 获取必须的access_token
        String access_token = tokenUtils.getAccessToken();
        String queryString = dataBaseProperties.getDatabaseUpdate()  + access_token;
        Map<String,Object> map = dataBaseProperties.getDbBody();
        // 组织post body
        map.put("query",String.format("db.collection('user-1').where({_openid: '%s'}).update({data:{index: 50}})",id));
        // json返回值
        String rawOutput = restTemplate.postForObject(queryString,map,String.class);
        // json对象映射
        UpdateReturn updateReturn = JSON.parseObject(rawOutput, UpdateReturn.class);
        // 获得errcode
        String errcode = updateReturn.getErrcode();
        if (!"0".equals(errcode)) {
            throw new DbErrorException(errcode);
        }
    }
//    @Update({"update User set index=50 where id=#{id}"})
    @Override
    public void setUserIndex75(String id) {
        // 获取必须的access_token
        String access_token = tokenUtils.getAccessToken();
        String queryString = dataBaseProperties.getDatabaseUpdate()  + access_token;
        Map<String,Object> map = dataBaseProperties.getDbBody();
        // 组织post body
        map.put("query",String.format("db.collection('user-1').where({_openid: '%s'}).update({data:{index: 75}})",id));
        // json返回值
        String rawOutput = restTemplate.postForObject(queryString,map,String.class);
        // json对象映射
        UpdateReturn updateReturn = JSON.parseObject(rawOutput, UpdateReturn.class);
        // 获得errcode
        String errcode = updateReturn.getErrcode();
        if (!"0".equals(errcode)) {
            throw new DbErrorException(errcode);
        }
    }





//    @Override
//    public void addUser(User user) {
//        String access_token = tokenUtils.getAccessToken();
//        String queryString = dataBaseProperties.getDatabaseAdd()  + access_token;
//        Map<String,Object> map = dataBaseProperties.getDbBody();
//        map.put("query",String.format("db.collection('user').add({data:[{username: '%s', password: '%s'}]})",user.getUsername(),user.getPassword()));
//        // json返回值
//        String rawOutput = restTemplate.postForObject(queryString,map,String.class);
//        // json对象映射
//        AddReturn addReturn = JSON.parseObject(rawOutput, AddReturn.class);
//        // 获得errcode
//        String errcode = addReturn.getErrcode();
//        // 这里实际上可以使用注解进行校验  参考codesheep
////        System.out.println(rawOutput);
//        if (!"0".equals(errcode)) {
//            throw new DbErrorException(addReturn.getErrmsg());
//        }
//    }
}
