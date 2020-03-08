package com.wizz.utils;

import com.wizz.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TokenUtilsTest {
    @Autowired
    TokenUtils tokenUtils;
    @Test
    public void getAccessToken() {
        String accessToken = tokenUtils.getAccessToken();
        System.out.println(accessToken);
    }

}