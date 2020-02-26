package com.wizz.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @descrip：文件上传的配置映射
 * @author: 李佳
 * @create： 2020-02-26-02:09
 **/
@ConfigurationProperties(
        prefix = "fileupload"
)
@Component
public class FileProperties {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
