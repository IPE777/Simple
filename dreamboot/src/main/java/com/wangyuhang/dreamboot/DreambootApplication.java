package com.wangyuhang.dreamboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wangyuhang.dreamboot.mapper")

public class DreambootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreambootApplication.class, args);
    }

}
