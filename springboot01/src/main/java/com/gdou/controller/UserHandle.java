package com.gdou.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gdou.MyUitls.DateTime;
import com.gdou.MyUitls.OperationRecord;
import com.gdou.dao.OperationMapper;
import com.gdou.entity.Capital;
import com.gdou.entity.User;
import com.gdou.service.CapitalService;
import com.gdou.service.RoleService;
import com.gdou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @Resource
    private OperationMapper operationMapper;


    @GetMapping("findAll/{page}/{size}")
    public IPage<User> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return userService.selectByPage(page, size);
    }

    @GetMapping("findByKey/{key}")
    public List<User> findByKey(@PathVariable("key") String k) {
        return userService.findByKey(k);
    }

    @PostMapping("/saveUser/{main_id}/{main_name}")
    public boolean saveUser(@RequestBody User user,
                            @PathVariable("main_id") String main_id,
                            @PathVariable("main_name") String main_name) {
        //RequestBody 可以把json对象转换成java对象

        user.setIntime(DateTime.getDT("yyyy-MM"));

        boolean save_user = userService.save(user);
        boolean save_role = roleService.RoleSave(user.getId(), user.getName(), user.getPhone());

        Capital capital = new Capital();
        capital.setUid(user.getId());
        capital.setName(user.getName());
        capital.setPosition(user.getPosition());
        boolean save_capital = capitalService.save(capital);
        if (save_user && save_capital && save_role) {
            String who = main_id + "-" + main_name;
            String what = user.getId() + "-" + user.getName();
            operationMapper.insert(OperationRecord.init(who, "增加", what));
            return true;
        } else {
            return false;
        }
        //return (save_user && save_capital && save_role);
    }

    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Integer id) {
        return userService.getById(id);
    }

    @PutMapping("/updateUser/{main_id}/{main_name}")
    public boolean updateUserById(@RequestBody User user,
                                  @PathVariable("main_id") String main_id,
                                  @PathVariable("main_name") String main_name) {
        String who = main_id + "-" + main_name;
        String what = user.getId() + "-" + user.getName();
        operationMapper.insert(OperationRecord.init(who, "更改", what));

        return userService.updateById(user);
    }

    @DeleteMapping("/deleteUser/{id}/{name}/{main_id}/{main_name}")
    public boolean deleteUserById(@PathVariable("id") Integer id,
                                  @PathVariable("name") String name,
                                  @PathVariable("main_id") String main_id,
                                  @PathVariable("main_name") String main_name) {
        String who = main_id + "-" + main_name;
        operationMapper.insert(OperationRecord.init(who, "删除", id + "-" + name));
        return userService.removeById(id);
    }
}
