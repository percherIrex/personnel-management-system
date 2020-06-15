package com.gdou.controller;

import com.gdou.MyUitls.Encryp;
import com.gdou.MyUitls.OperationRecord;
import com.gdou.dao.OperationMapper;
import com.gdou.entity.Role;
import com.gdou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/login")
public class RoleHandle {

    @Autowired
    private RoleService roleService;

    @Resource
    private OperationMapper operationMapper;

    //登录功能验证
    @GetMapping("/{usn}/{font_key}")
    public Role login(@PathVariable("usn") String usn, @PathVariable("font_key") String pwd) {

        Role role = roleService.findProper(usn);
        if (role == null) {
            return null;
        }
        String key = Encryp.encryption(role.getUsername(), role.getPassword());
        if (key.equals(pwd)){
            return role;
        }else {
            return null;
        }
    }

    @PutMapping("/editRole/{uid}/{method}/{name}/{main_id}/{main_name}")
    public boolean editRole(@PathVariable("uid") Integer uid,
                            @PathVariable("method") Integer m,
                            @PathVariable("name") String name,
                            @PathVariable("main_id") String main_id,
                            @PathVariable("main_name") String main_name) {
        String who = main_id + "-" + main_name;
        operationMapper.insert(OperationRecord.init(who, "变更权限", uid + "-" + name));
        return roleService.editRole(uid, m);
    }

    @GetMapping("/getRole")
    public List<Role> getRole() {
        return roleService.list();
    }

    @PutMapping("/{uid}/{pwd}/{old}")
    boolean changePW(@PathVariable("uid") Integer uid,
                     @PathVariable("pwd") String pwd,
                     @PathVariable("old") String old) {
        boolean ret = false;
        try {
            Role role = roleService.getById(uid);
            if (old.equals(role.getPassword())) {
                role.setPassword(pwd);
                roleService.updateById(role);
                ret = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return ret;
        }
    }
}
