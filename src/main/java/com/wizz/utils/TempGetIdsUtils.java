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
 * @descrip：临时获得学校提供的ids数据，注意只是项目初始化的时候，可以使用
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
    /** @Description: 获得ids的access_token，需要注意的是使用ids的过程中并未判断accessToken是否失效
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
    /** @Description: 分页查询学生数据
    * @Param: [accessToken, page]
    * @return: com.alibaba.fastjson.JSONArray
    * @Author: 李佳
    * @Date: 2020/3/19
    */

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
    /** @Description: 分页查询院系组织数据
    * @Param: [accessToken, page]
    * @return: com.alibaba.fastjson.JSONArray
    * @Author: 李佳
    * @Date: 2020/3/19
    */

    public JSONArray getPageDataDepartment (String accessToken, String page) {
        String url = idsProperties.getGetIdsDepartment() + accessToken + "&per_page=" + idsProperties.getGetIdsTokenParams() +"&page=" + page;
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
    /** @Description: 向数据库导入学生信息
    * @Param: []
    * @return: java.lang.String
    * @Author: 李佳
    * @Date: 2020/3/19
    */

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
                // 数据库字段的信息的预解析
                String name = jsonObject.getString("XM");
                String number = jsonObject.getString("XH");
                String classB = jsonObject.getString("YXDM");
                String classC = number.substring(0,2);
                String classA = "研究生";
                map.put("query",String.format("db.collection('user').add({data:[{number: '%s', name: '%s',classB: '%s',classC: '%s',classA: '%s'}]})",number,name,classB,classC,classA));
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
    /** @Description: 向数据库中导入院系组织信息
    * @Param: []
    * @return: java.lang.String
    * @Author: 李佳
    * @Date: 2020/3/19
    */

    public String dbTransferDepartment () {
        String accessToken = getIdsAccessToken();
        Integer page = 1;
        while (getPageDataDepartment(accessToken,String.valueOf(page)) != null) {
            // 这里写的比较烂
            JSONArray data = getPageDataDepartment(accessToken,String.valueOf(page));
            String token = tokenUtils.getAccessToken();
            String queryString = dataBaseProperties.getDatabaseAdd()  + token;
            Map<String,Object> map = dataBaseProperties.getDbBody();
            for (Iterator<Object> iterator = data.iterator(); iterator.hasNext();) {
                JSONObject jsonObject = (JSONObject) iterator.next();
                String depID = jsonObject.getString("XSH");
                String depName = jsonObject.getString("XSM");
                map.put("query",String.format("db.collection('department').add({data:[{depId: '%s', depName: '%s'}]})",depID,depName));
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
