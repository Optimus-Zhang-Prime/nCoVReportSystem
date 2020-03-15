package com.wizz.entity;

import lombok.*;

/**
 * @descrip：封装前端的打卡信息的location
 * @author: 李佳
 * @create： 2020-03-15-10:11
 **/
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReportLocation {
    @Getter
    @Setter
    private String lat;
    @Getter
    @Setter
    private String lng;

}
