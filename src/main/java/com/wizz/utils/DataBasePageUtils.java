package com.wizz.utils;

import org.springframework.stereotype.Component;

/**
 * @descrip：数据库分页工具
 * @author: 李佳
 * @create： 2020-03-02-15:01
 **/
@Component
public class DataBasePageUtils {
    public Integer getSkip (Integer page) {
        return 50 * (page -1);
    }
}
