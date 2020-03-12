package com.wizz.utils;

import com.alibaba.fastjson.JSON;
import com.wizz.entity.jsonReturn.GetTokenReturn;
import com.wizz.entity.jsonReturn.RedisCacheReturn;
import com.wizz.exception.DbErrorException;
import com.wizz.property.DataBaseProperties;
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
import java.util.HashMap;
import java.util.Map;

/**
 * @descrip：获取云开发数据库的access_token
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
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        String nonce = RandomStringUtils.randomAlphanumeric(5);
        map.add("nonce", nonce);
        map.add("sign",getMD5(nonce+"yiqingtong2020"));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        long before = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request , String.class );
        long after = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        logger.debug("请求时间" + String.valueOf(after-before));
        if (response.getStatusCodeValue() != 200) {
            throw new DbErrorException("redis缓存获取失败");
        }
        Object parse = JSON.parse(response.getBody());
        String parseBody = JSON.toJSONString(parse);
        RedisCacheReturn result = JSON.parseObject(parseBody, RedisCacheReturn.class);
        logger.debug(result.toString());
        return result.getAccess_token();
    }
    public String getMD5(String str) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
