package com.news_release;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.news_release.mapper")
public class NewsReleaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsReleaseApplication.class, args);
    }

}
