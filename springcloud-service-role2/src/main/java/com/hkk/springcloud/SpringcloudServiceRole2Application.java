package com.hkk.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient// 开启Eureka客户端
@SpringBootApplication
public class SpringcloudServiceRole2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudServiceRole2Application.class, args);
    }

}
