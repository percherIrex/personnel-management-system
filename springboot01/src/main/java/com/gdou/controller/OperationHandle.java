package com.gdou.controller;

import com.gdou.entity.Operation;
import com.gdou.service.OperationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/operation")
public class OperationHandle {

    @Resource
    private OperationService operationService;

    @GetMapping
    public List<Operation> List(){
        return operationService.list();
    }

    @GetMapping("/{key}")
    public List<Operation> KeyList(@PathVariable("key") String key){
        return operationService.findByKey(key);
    }
}
