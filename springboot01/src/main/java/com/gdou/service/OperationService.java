package com.gdou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdou.entity.Operation;
import com.gdou.entity.User;

import java.util.List;

public interface OperationService extends IService<Operation> {
    List<Operation> findByKey(String key);
}
