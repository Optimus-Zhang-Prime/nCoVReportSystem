package com.wizz.utils;

import com.alibaba.fastjson.JSON;
import com.wizz.entity.jsonReturn.GetTokenReturn;
import com.wizz.property.DataBaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

/**
 * @descrip：获取access_token
 * @author: 李佳
 * @create： 2020-02-26-03:02
 **/
@Component
public class TokenUtils {
    @Autowired
    private DataBaseProperties dataBaseProperties;
    RestTemplate restTemplate = new RestTemplate();
    public String getAccessToken () {
        // 以下这些获取属性的语句不能写在方法的外边否则导致自动注入失败
        String access_tokenUrl = dataBaseProperties.getAccessToken();
        Map<String,Object> map = dataBaseProperties.getGetTokenParams();
        GetTokenReturn result = JSON.parseObject(restTemplate.getForObject(access_tokenUrl,String.class,map), GetTokenReturn.class);
        return result.getAccess_token();
    }
}
