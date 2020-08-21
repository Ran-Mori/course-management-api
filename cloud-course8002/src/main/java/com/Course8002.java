package com;

import org.apache.shiro.spring.boot.autoconfigure.ShiroAnnotationProcessorAutoConfiguration;
import org.apache.shiro.spring.boot.autoconfigure.ShiroAutoConfiguration;
import org.apache.shiro.spring.boot.autoconfigure.ShiroBeanAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        ShiroAnnotationProcessorAutoConfiguration.class,
        ShiroAutoConfiguration.class,
        ShiroBeanAutoConfiguration.class})
@MapperScan(value = "com.mapper")
public class Course8002 {
    public static void main(String[] args) {
        SpringApplication.run(Course8002.class,args);
    }
}
