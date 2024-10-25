package com.springcloud.user.service;

import com.hkk.springcloud.model.User;

public interface IUserService {

    User selectPrimaryKey(Long id);
}
