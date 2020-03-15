package com.gdou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.dao.CapitalMapper;
import com.gdou.entity.Capital;
import com.gdou.service.CapitalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CapitalServiceImpl extends ServiceImpl<CapitalMapper, Capital> implements CapitalService {

    @Resource
    private CapitalMapper capitalMapper;
}
