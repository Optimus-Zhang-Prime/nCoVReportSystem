package com.wizz.entity.jsonReturn;

import lombok.*;

import java.util.Map;

/**
 * @descrip：腾讯API的返回解析  https://lbs.qq.com/webservice_v1/guide-gcoder.html
 * @author: 李佳
 * @create： 2020-03-15-13:11
 **/
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdcodeReturn {
    @Getter
    @Setter
    private Integer status;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String request_id;
    @Getter
    @Setter
    private Map<String,Object> result;
}
