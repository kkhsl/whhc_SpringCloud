package com.hkk.springcloud.service;


import org.springframework.stereotype.Component;

@Component
public class RoleRemoteFallBack implements RoleRemoteService{

    /**
     * 服务降级方法（实现服务降级-但没有错误信息）
     * @param id
     * @return
     */
    @Override
    public Object getRoles(String id) {
        return "服务降级";
    }
}
