package com.gdou.controller;

import com.gdou.MyUitls.OperationRecord;
import com.gdou.dao.OperationMapper;
import com.gdou.entity.Capital;
import com.gdou.entity.myEntity.CapitalNeed;
import com.gdou.service.CapitalService;
import com.gdou.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/capital")
public class CapitalHandle {

    @Autowired
    private CapitalService capitalService;
    @Resource
    private RecordService recordService;
    @Resource
    private OperationMapper operationMapper;

    @GetMapping("/list")
    public List<Capital> listCapital()
    {
        return capitalService.list();
    }

    @PutMapping("/update/{main_id}/{main_name}")
    public boolean updateById(@RequestBody Capital capital,
                              @PathVariable("main_id") String main_id,
                              @PathVariable("main_name") String main_name){

        operationMapper.insert(OperationRecord.init(main_id+"-"+main_name,"修改","资金参数"));

        return capitalService.updateById(capital);
    }

    @GetMapping("/list/final")
    public List<CapitalNeed> listCapitalNeed(){

        List<CapitalNeed> capitalNeedList = new ArrayList<>();
        List<Capital> capitalList = capitalService.list();

        for(Capital c:capitalList){
            //System.out.println(c);
            CapitalNeed capitalNeed = new CapitalNeed(c);
            capitalNeed.setSign(recordService.countOfSomeOneRecord(c.getUid(),true));
            capitalNeed.setAbsent(recordService.countOfSomeOneRecord(c.getUid(),false));
            capitalNeedList.add(capitalNeed);
        }

        return capitalNeedList;
    }

}
