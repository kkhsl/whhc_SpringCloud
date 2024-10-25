package com.hkk.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard// 开启仪表盘功能
@SpringBootApplication
public class SpringcloudServiceDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudServiceDashboardApplication.class, args);
    }

}
