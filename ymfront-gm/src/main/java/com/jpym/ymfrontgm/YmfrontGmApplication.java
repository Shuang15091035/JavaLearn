package com.jpym.ymfrontgm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan( value = "com.jpym.ymfrontgm.dao" )
public class YmfrontGmApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(YmfrontGmApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(YmfrontGmApplication.class);
    }
}
