package com.gdou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.dao.RoleMapper;
import com.gdou.entity.Role;
import com.gdou.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//接口实现类
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    //新增员工时候同时也向权限表添加Role
    @Override
    public boolean RoleSave(Integer uid, String name,String username) {
        Role role = new Role();
        role.setName(name);
        role.setUid(uid);
        role.setUsername(username);
        //默认权限为0 普通用户
        role.setAuthor(false);
        if (roleMapper.insert(role) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Role findProper(String usn) {
        return roleMapper.loginRole(usn);
    }

    @Override
    public boolean editRole(Integer uid, Integer method) {
        if(method==1){
            return roleMapper.editRole_1(uid);
        }else {
            return roleMapper.editRole_2(uid);
        }
    }
}
