package com.springcloud.user.mappper;

import com.hkk.springcloud.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User selectPrimaryKey(Long id);
}
