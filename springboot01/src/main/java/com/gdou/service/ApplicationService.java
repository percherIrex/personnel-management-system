package com.gdou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdou.entity.Application;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ApplicationService extends IService<Application> {
    Application is_Ex(Integer uid,String date);

    List<Application> listByUid(Integer uid);

    List<Application> listByHandle();
}
