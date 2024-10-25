package com.hkk.springcloud.mapper;

import com.hkk.springcloud.model.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {


    /**
     * 根据角色id查询指定角色
     * @param id
     * @return
     */
    List<Role> findRoleById(Long id);


    List<Role> findRolesByUserId(Long id);

}
