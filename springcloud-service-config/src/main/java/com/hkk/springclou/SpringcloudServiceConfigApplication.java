package com.hkk.springclou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient// 开启Eureka客户端
@EnableConfigServer // 开启 Spring Cloud 配置中心支持
@SpringBootApplication
public class SpringcloudServiceConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudServiceConfigApplication.class, args);
    }

}
