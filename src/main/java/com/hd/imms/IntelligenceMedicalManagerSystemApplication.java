package com.hd.imms;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;


//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@Slf4j
@MapperScan("com.hd.imms.mapper")
@EnableCaching
public class IntelligenceMedicalManagerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntelligenceMedicalManagerSystemApplication.class, args);
    }

}
