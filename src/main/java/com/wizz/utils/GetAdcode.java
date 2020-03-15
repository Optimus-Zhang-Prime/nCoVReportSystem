package com.wizz.utils;

import com.alibaba.fastjson.JSONObject;
import com.wizz.entity.jsonReturn.AdcodeReturn;
import com.wizz.exception.DbErrorException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @descrip：经纬度转adcode
 * @author: 李佳
 * @create： 2020-03-15-13:05
 **/
public class GetAdcode {
    static private RestTemplate restTemplate = new RestTemplate();
    public static Map<String,String> getAdcode (String lon,String lat,String key) {
        String url = String.format("https://apis.map.qq.com/ws/geocoder/v1/?location=%s,%s&key=%s&get_poi=0",lat,lon,key);
        System.out.println(url);
        AdcodeReturn adcodeReturn = restTemplate.getForObject(url, AdcodeReturn.class);
        if (adcodeReturn.getStatus() != 0) {
            throw new DbErrorException("修改/替代打卡失败" + adcodeReturn.getMessage());
        }
        Map<String,Object> resultMap = adcodeReturn.getResult();
        String address = (String) resultMap.get("address");
        Map<String,Object> adMap = (Map<String, Object>) resultMap.get("ad_info");
        String adcode = (String) adMap.get("adcode");
        Map<String,String> result = new HashMap<>();
        result.put("adcode",adcode);
        result.put("address",address);
        return result;
    }
}
