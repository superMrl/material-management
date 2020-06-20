package com.jckj.materialmanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jckj.materialmanagement.mapper") //扫描dao
public class MaterialManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaterialManagementApplication.class, args);
    }

}
