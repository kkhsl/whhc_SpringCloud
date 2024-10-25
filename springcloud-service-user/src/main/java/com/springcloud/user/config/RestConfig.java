package com.springcloud.user.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {


    @LoadBalanced //Ribbon实现负载均衡的 使用调用（Spring Cloud ->封装 Ribbon + Eureka + RestTemplate）
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 核心组件
     *
     * @return
     */
    public IRule iRule() {
        return new RoundRobinRule();
    }
}
