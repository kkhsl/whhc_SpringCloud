package com.springcloud.user.service.impl;

import com.hkk.springcloud.model.User;
import com.springcloud.user.mappper.UserMapper;
import com.springcloud.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectPrimaryKey(Long id) {
        return this.userMapper.selectPrimaryKey(id);
    }
}
