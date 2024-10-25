package com.springcloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/*
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker //开启Hystrix 断路器
*/
@EnableZuulProxy// 开启Zuul网关支持
@SpringBootApplication
//@SpringCloudApplication // 等价以上三个注解之和
public class SpringcloudServiceZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudServiceZuulApplication.class, args);
    }

}
