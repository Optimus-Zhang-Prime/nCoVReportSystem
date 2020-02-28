package com.wizz.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wizz.entity.jsonReturn.AddReturn;
import com.wizz.entity.jsonReturn.IdsReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.property.DataBaseProperties;
import com.wizz.property.IdsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.Map;

/**
 * @descrip：临时获得ids数据，注意只是项目初始化的时候，可以使用
 * @author: 李佳
 * @create： 2020-02-27-19:01
 **/
@Component
public class TempGetIdsUtils {
    @Autowired
    DataBaseProperties dataBaseProperties;
    @Autowired
    TokenUtils tokenUtils;
    @Autowired
    private IdsProperties idsProperties;
    RestTemplate restTemplate = new RestTemplate();
    /** @Description: 获得ids的access_token
    * @Param: []
    * @return: java.lang.String
    * @Author: 李佳
    * @Date: 2020/2/27
    */
    
    String getIdsAccessToken () {
        String url = idsProperties.getIdsAccessToken();
        String rawoutput = restTemplate.getForObject(url,String.class);
        IdsReturn idsReturn = JSON.parseObject(rawoutput, IdsReturn.class);
        String message = idsReturn.getMessage();
        if (!"ok".equals(message)) {
            throw new DbErrorException(message);
        }
        String output = (String) idsReturn.getResult().get("access_token");
        return output;
    }
    public JSONArray getPageData (String accessToken, String page) {
        String url = idsProperties.getGetIdsData() + accessToken + "&per_page=" + idsProperties.getGetIdsTokenParams() +"&page=" + page;
        String rawOutput = restTemplate.getForObject(url,String.class);
        IdsReturn idsReturn = JSON.parseObject(rawOutput, IdsReturn.class);
        Map<String,Object> result = idsReturn.getResult();
        if (result == null) {
            return null;
        } else {
            // 复杂类型可以使用fastJSON的内置类型
            return (JSONArray) result.get("data");
        }
    }
    public String dbTransfer () {
        String accessToken = getIdsAccessToken();
        Integer page = 1;
        while (getPageData(accessToken,String.valueOf(page)) != null) {
            // 这里写的比较烂
            JSONArray data = getPageData(accessToken,String.valueOf(page));
            String token = tokenUtils.getAccessToken();
            String queryString = dataBaseProperties.getDatabaseAdd()  + token;
            Map<String,Object> map = dataBaseProperties.getDbBody();
            for (Iterator<Object> iterator = data.iterator(); iterator.hasNext();) {
                JSONObject jsonObject = (JSONObject) iterator.next();
                String name = jsonObject.getString("XM");
                String number = jsonObject.getString("XH");
                map.put("query",String.format("db.collection('user').add({data:[{number: '%s', name: '%s'}]})",number,name));
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
            page ++;
        }
        return "成功";
    }
}
