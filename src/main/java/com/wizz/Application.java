package com.wizz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //标注主程序类
public class Application {
    public static void main(String[] args) {
        //启动spring应用
        SpringApplication.run(Application.class, args);
    }
}
