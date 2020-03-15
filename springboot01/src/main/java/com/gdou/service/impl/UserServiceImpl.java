package com.gdou.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.dao.UserMapper;
import com.gdou.entity.User;
import com.gdou.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public IPage<User> selectByPage(int currentPage, int size) {
        Page<User> page = new Page<>(currentPage,size);
        IPage<User> userIPage = userMapper.selectPage(page,null);
        //return userIPage.getRecords();
        return userIPage;
    }

    @Override
    public List<User> findByKey(String key) {
        return userMapper.findByKey(key);
    }

    @Override
    public Integer getTotal() {
        return userMapper.selectCount(null);
    }

}
