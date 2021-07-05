package com.chat.test.sjj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chat.test.sjj.mapper")
public class ChatBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatBoxApplication.class, args);
    }

}
