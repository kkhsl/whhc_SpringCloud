package com.hkk.springcloud.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

// 获取具体的详细信息
//  熔断器 类
@Component
public class RoleRemoteFallBackFactory implements FallbackFactory<RoleRemoteService> {

    @Override
    public RoleRemoteService create(Throwable throwable) {
        return new RoleRemoteService() {
            @Override
            public Object getRoles(String id) {
                String message = throwable.getMessage();
                System.out.println("feign 远程调用异常：" + message);
                return "feign 远程调用异常：";
            }
        };
    }
}
