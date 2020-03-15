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
    /** @Description: 用于把address请求参数转变为ReportLocation类型进行解析
    * @Param: [s]
    * @return: com.wizz.entity.ReportLocation
    * @Author: 李佳
    * @Date: 2020/3/15
    */
    
    @Override
    public ReportLocation convert(String s) {
        return JSON.parseObject(s, ReportLocation.class);
    }
}
