package com.springcloud.user;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;



/*
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker //开启Hystrix 断路器 @EnableHystrix
*/
@ComponentScan(basePackages = {"com.hkk.springcloud.service","com.springcloud.user"})

@EnableHystrixDashboard
// 开启Feign并扫描Feign客户端
@EnableFeignClients({"com.hkk.springcloud.service","com.springcloud.user."})
@SpringCloudApplication // 等价以上三个注解之和
public class SpringcloudServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudServiceUserApplication.class, args);
    }

    //springboot2.0以上版本，使用hystrix的dashboard要配置一个servlet
//    @Bean
//    public ServletRegistrationBean getServlet() {
//        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
//        registrationBean.setLoadOnStartup(1);
//        registrationBean.addUrlMappings("/actuator/hystrix.stream");
//        registrationBean.setName("HystrixMetricsStreamServlet");
//        return registrationBean;
//    }


}
