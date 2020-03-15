package com.wizz.component;

import com.alibaba.fastjson.JSON;
import com.wizz.entity.ReportLocation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @descrip：自定义的参数转换器
 * @author: 李佳
 * @create： 2020-03-15-11:28
 **/
@Component
public class MyConverter implements Converter<String, ReportLocation> {
    @Override
    public ReportLocation convert(String s) {
        return JSON.parseObject(s, ReportLocation.class);
    }
}
