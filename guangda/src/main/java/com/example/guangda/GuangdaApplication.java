package com.example.guangda;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching  //开启缓存
@SpringBootApplication
@MapperScan("com.example.guangda.mapper")
public class GuangdaApplication{

    public static void main(String[] args) {
        SpringApplication.run(GuangdaApplication.class, args);
    }

}
