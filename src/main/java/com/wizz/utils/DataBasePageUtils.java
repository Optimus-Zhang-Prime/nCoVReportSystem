package com.wizz.utils;

import org.springframework.stereotype.Component;

/**
 * @descrip：数据库分页工具
 * @author: 李佳
 * @create： 2020-03-02-15:01
 **/
@Component
public class DataBasePageUtils {
    /** @Description: 简单地计算分页，一页50条数据，得到的是skip的参数值
    * @Param: [page]
    * @return: java.lang.Integer
    * @Author: 李佳
    * @Date: 2020/3/19
    */
    public Integer getSkip (Integer page) {
        return 50 * (page -1);
    }
}
