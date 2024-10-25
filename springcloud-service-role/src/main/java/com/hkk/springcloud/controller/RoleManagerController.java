package com.hkk.springcloud.controller;

import com.hkk.springcloud.model.Role;
import com.hkk.springcloud.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("/角色管理")
@RestController
@RequestMapping("/admin/role")
public class RoleManagerController {

    @Autowired
    private IRoleService roleSerice;

    @ApiOperation("角色列表")
    @GetMapping("/list")
    public Object list(){
        return this.roleSerice.findRoleById(Long.parseLong("23"));
    }

    @RequestMapping(value = "/user/key", method = RequestMethod.GET)
    public Object getRoles(@RequestParam("id") String id){
        System.out.println("role1");

        // 测试Ribbon接口超时
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Role> roleList = this.roleSerice.findRolesByUserId(Long.parseLong(id));
        return roleList;
    }

    @RequestMapping("/lists")
    public Object getRoles(){
        System.out.println("role1");
        List<Role> roleList = this.roleSerice.findRolesByUserId(Long.parseLong("23"));
        return roleList;
    }

    /**
     * 远程调用
     * 根据用户ID获取所有角色
     * @return
     */
    public Object findRoleByUserId(){
        // 远程调用UserManagerController 查询用户是否真实
       /* this.roleSerice.*/
        return null;
    }
}
