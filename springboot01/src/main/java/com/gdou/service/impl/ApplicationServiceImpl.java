package com.gdou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.dao.ApplicationMapper;
import com.gdou.entity.Application;
import com.gdou.service.ApplicationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {

    @Resource
    private ApplicationMapper appMapper;

    @Override
    public Application is_Ex(Integer uid, String date) {
        return appMapper.is_Ex(uid, date);
    }

    @Override
    public List<Application> listByUid(Integer uid) {
        return appMapper.listByUid(uid);
    }

    @Override
    public List<Application> listByHandle() {
        return appMapper.listByHandle();
    }
}
