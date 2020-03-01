package com.example.springboot001demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication  //springboot 程序主入口
@EnableSwagger2
public class Springboot001demoApplication {

    public static void main(String[] args) {

        SpringApplication.run(Springboot001demoApplication.class, args);
    }

}
