package com.hkk.springcloud.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 声明当前类是一个Feign客户端
 * Feign 接口提供者
 */
@FeignClient(value = "spring-cloud-role-service",
        //fallback = RoleRemoteFallBack.class
        fallbackFactory = RoleRemoteFallBackFactory.class)
public interface RoleRemoteService {

    /**
     * 声明一个feign接口，他的实现是服务提供者的Controller实现
     * @param
     * @return
     */
    @RequestMapping(value = "/admin/role/user/key", method = RequestMethod.GET)
    Object getRoles(@RequestParam("id") String id);

}
