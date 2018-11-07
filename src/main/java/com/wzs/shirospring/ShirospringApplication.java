package com.wzs.shirospring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@MapperScan(value = "com.wzs.shirospring.mapper")
public class ShirospringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShirospringApplication.class, args);
    }
}
