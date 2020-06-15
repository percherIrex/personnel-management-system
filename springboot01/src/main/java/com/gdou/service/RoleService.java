package com.gdou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdou.entity.Role;

//接口
public interface RoleService extends IService<Role> {

    boolean RoleSave(Integer uid,String name,String username);

    Role findProper(String usn);

    boolean editRole(Integer uid,Integer method);
}
