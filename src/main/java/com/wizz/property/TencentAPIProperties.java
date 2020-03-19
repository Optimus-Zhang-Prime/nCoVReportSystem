package com.wizz.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * @descrip：腾讯地图API的配置
 * @author: 李佳
 * @create： 2020-03-15-13:32
 **/
@ConfigurationProperties(prefix = "tencentmap")
@Service
@AllArgsConstructor
@NoArgsConstructor
public class TencentAPIProperties {
    @Setter
    @Getter
    private String key;
}
