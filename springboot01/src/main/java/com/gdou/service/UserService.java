package com.gdou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gdou.entity.User;

import java.util.List;


public interface UserService extends IService<User> {

    IPage<User> selectByPage(int currentPage, int size);

    List<User> findByKey(String key);

    Integer getTotal();
}
