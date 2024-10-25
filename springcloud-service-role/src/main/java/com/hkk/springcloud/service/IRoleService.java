package com.hkk.springcloud.service;


import com.hkk.springcloud.model.Role;

import java.util.List;

public interface IRoleService {
    /**
     * 根据角色id查询指定角色
     * @param id
     * @return
     */
    List<Role> findRoleById(Long id);

    List<Role> findRolesByUserId(Long id);
}
