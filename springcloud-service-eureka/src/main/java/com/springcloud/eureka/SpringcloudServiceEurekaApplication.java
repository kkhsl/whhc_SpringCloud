package com.springcloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer // 开启Eureka服务
@SpringBootApplication
public class SpringcloudServiceEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudServiceEurekaApplication.class, args);
    }

}
