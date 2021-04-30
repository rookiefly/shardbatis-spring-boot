package com.rookiefly.open.shardbatis.samples;

import com.rookiefly.open.shardbatis.samples.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.rookiefly.open.shardbatis.samples.mapper")
@SpringBootApplication
public class ShardbatisSampleApplication implements CommandLineRunner {

    @Autowired
    private UserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(ShardbatisSampleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        userMapper.findAll().forEach(System.out::println);
        userMapper.findById(1).forEach(System.out::println);
    }

}
