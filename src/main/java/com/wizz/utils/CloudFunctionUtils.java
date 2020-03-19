package com.wizz.utils;

import com.wizz.entity.jsonReturn.CloudFunctionReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.property.DataBaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @descrip：云函数调用工具
 * @author: 李佳
 * @create： 2020-03-02-22:42
 **/
@Component
public class CloudFunctionUtils {
    @Autowired
    private DataBaseProperties dataBaseProperties;
    @Autowired
    private TokenUtils tokenUtils;
    private RestTemplate restTemplate = new RestTemplate();
    public String InvokeFunction (String func,Map<String,Object>argMap) {
        String token = tokenUtils.getAccessToken();
        String Rawurl = dataBaseProperties.getCloudFunction() + token;
        String url = String.format(Rawurl+"&env=%s&name=%s",dataBaseProperties.getDbBody().get("env"),func);
        // 云函数参数
        Map<String,Object> map = new HashMap<>();
        map.putAll(argMap);
        // 执行云函数
        CloudFunctionReturn cloudFunctionReturn = restTemplate.postForObject(url, map, CloudFunctionReturn.class);
        String errcode = cloudFunctionReturn.getErrcode();
        // 这里实际上可以使用注解进行校验  参考codesheep
        if (!"0".equals(errcode)) {
            throw new DbErrorException(cloudFunctionReturn.getErrmsg());
        }
        return cloudFunctionReturn.getResp_data();
    }
}
