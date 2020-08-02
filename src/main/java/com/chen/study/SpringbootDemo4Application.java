package com.chen.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringbootDemo4Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo4Application.class, args);
    }

}
