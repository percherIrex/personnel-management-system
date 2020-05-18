package com.gdou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.dao.OperationMapper;
import com.gdou.entity.Operation;
import com.gdou.service.OperationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OperationServiceImpl extends ServiceImpl<OperationMapper, Operation> implements OperationService {

    @Resource
    private OperationMapper operationMapper;

    @Override
    public List<Operation> findByKey(String key) {
        return operationMapper.findByKey(key);
    }
}
