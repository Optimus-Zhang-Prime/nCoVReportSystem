package com.wizz.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.wizz.entity.jsonReturn.RedisCacheReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.property.RedisCacheProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @descrip：获取云开发数据库的access_token，使用的是韩翔宇的Redis服务
 * @author: 李佳
 * @create： 2020-02-26-03:02
 **/
@Component
public class TokenUtils {
    @Autowired
    private RedisCacheProperties redisCacheProperties;
    RestTemplate restTemplate = new RestTemplate();
    Logger logger = LoggerFactory.getLogger(getClass());
    public String getAccessToken () {
        HttpHeaders headers = new HttpHeaders();
        String url = redisCacheProperties.getUrl();
        // 使用form_urlencode的请求体格式
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        String nonce = RandomStringUtils.randomAlphanumeric(5);
        String md5 = getMD5(nonce+"yiqingtong2020");
        // 在请求体重加入签名
        map.add("nonce", nonce);
        map.add("sign",md5);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        long before = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request , String.class );
        long after = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        logger.debug("请求时间" + String.valueOf(after-before));
        if (response.getStatusCodeValue() != 200) {
            throw new DbErrorException("redis缓存获取失败");
        }
        // 利用catch检测下为什么会出现com.alibaba.fastjson.JSONException: syntax error, pos 9, json : SignError的错误
        Object parse = null;
        try {
            parse = JSON.parse(response.getBody());
        } catch(JSONException e) {
            logger.error(e.getMessage() + "-----" + response.getBody());
            logger.error("nonce" + nonce);
            logger.error("md5" + md5);
            throw new RuntimeException(e.getMessage());
        }
        String parseBody = JSON.toJSONString(parse);
        RedisCacheReturn result = JSON.parseObject(parseBody, RedisCacheReturn.class);
        logger.debug(result.toString());
        return result.getAccess_token();
    }
    /** @Description: md5计算
    * @Param: [str]
    * @return: java.lang.String
    * @Author: 李佳
    * @Date: 2020/3/19
    */
    
    public String getMD5(String str) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        int length = md5code.length();
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - length; i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
