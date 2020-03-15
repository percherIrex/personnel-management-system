package com.gdou.controller;

import com.gdou.entity.Role;
import com.gdou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/login")
public class RoleHandle {

    @Autowired
    private RoleService roleService;

    @GetMapping("/{usn}/{pwd}")
    public Role login(@PathVariable("usn") String usn, @PathVariable("pwd") String pwd){
        return roleService.findProper(usn, pwd);
    }

    @PutMapping("/editRole/{uid}/{method}")
    public boolean editRole(@PathVariable("uid") Integer uid,@PathVariable("method") Integer m){
        return roleService.editRole(uid,m);
    }

    @GetMapping("/getRole")
    public List<Role> getRole(){
        return roleService.list();
    }

}
