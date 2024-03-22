package com.kgc.easybuy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.kgc.easybuy.dao")
public class EasyBuyApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyBuyApplication.class, args);
    }

}
