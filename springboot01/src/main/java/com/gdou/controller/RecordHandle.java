package com.gdou.controller;

import com.gdou.dao.UserMapper;
import com.gdou.entity.Record;
import com.gdou.entity.myEntity.Count;
import com.gdou.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordHandle {

    @Autowired
    private RecordService recordService;
    @Resource
    private UserMapper userMapper;


    @PostMapping("/saveRecord")
    public boolean saveRecord(@RequestBody Record record) {
        return recordService.dailySign(record);
    }

    @GetMapping("/signList/{method}/{uid}")
    public List<Record> signList(@PathVariable("uid") Integer uid,@PathVariable("method") Integer m){
        if(m==0){
            return recordService.allToday();
        }else if(m==1) {
            return recordService.mySignToday(uid);
        }else if(m==2){
            return recordService.myAbsentToday(uid);
        }else {
            return recordService.allAbsent();
        }
    }

    @PostMapping("/saveRecordAbsent")
    public boolean absentApply(@RequestBody Record record) {
        return recordService.absentApply(record);
    }

    @GetMapping("/count")
    public Count countOfRecord(){
        Count count=new Count();
        count.setCof_absent(recordService.countOfAbsent());
        count.setCof_sign(recordService.countOfSign());
        count.setCof_total(userMapper.selectCount(null));

        return count;
    }

    @GetMapping("/salary/{uid}/{method}")
    public Integer countOFSomeOne(@PathVariable("uid") Integer uid,@PathVariable("method") Integer m){
        if(m==1){
            return recordService.countOfSomeOneRecord(uid,true);
        }else {
            return recordService.countOfSomeOneRecord(uid, false);
        }
    }
}
