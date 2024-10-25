package com.hkk.springcloud.service.impl;

import com.hkk.springcloud.mapper.RoleMapper;
import com.hkk.springcloud.model.Role;
import com.hkk.springcloud.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRoleById(Long id) {
        return this.roleMapper.findRoleById(id);
    }

    @Override
    public List<Role> findRolesByUserId(Long id) {
        return this.roleMapper.findRolesByUserId(id);
    }
}
