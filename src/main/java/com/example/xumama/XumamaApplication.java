package com.example.xumama;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.xumama.mapper")
public class XumamaApplication {

    public static void main(String[] args) {
        SpringApplication.run(XumamaApplication.class, args);
        System.out.println("xumama run successful!!");
    }

}
