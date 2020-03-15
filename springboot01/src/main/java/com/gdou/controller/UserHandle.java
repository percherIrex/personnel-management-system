package com.gdou.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gdou.entity.Capital;
import com.gdou.entity.User;
import com.gdou.service.CapitalService;
import com.gdou.service.RoleService;
import com.gdou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandle {


    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CapitalService capitalService;


    @GetMapping("findAll/{page}/{size}")
    public IPage<User> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return userService.selectByPage(page, size);
    }

    @GetMapping("findByKey/{key}")
    public List<User> findByKey(@PathVariable("key") String k) {
        return userService.findByKey(k);
    }

    @PostMapping("/saveUser")
    public boolean saveUser(@RequestBody User user) {
        //RequestBody 可以把json对象转换成java对象
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        user.setIntime(simpleDateFormat.format(date));
        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");



        boolean save_user = userService.save(user);
        boolean save_role = roleService.RoleSave(user.getId(), user.getName(), simpleDateFormat.format(date));

        Capital capital = new Capital();
        capital.setUid(user.getId());
        capital.setName(user.getName());
        capital.setPosition(user.getPosition());
        boolean save_capital = capitalService.save(capital);
        return (save_user&&save_capital&&save_role);
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Integer id) {
        return userService.getById(id);
    }

    @PutMapping("/updateUser")
    public boolean updateUserById(@RequestBody User user) {
        return userService.updateById(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUserById(@PathVariable("id") Integer id) {
        return userService.removeById(id);
    }
}
