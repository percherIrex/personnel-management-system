package com.gdou.controller;

import com.gdou.MyUitls.DateTime;
import com.gdou.dao.RecordMapper;
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
    @Resource
    private RecordMapper recordMapper;


    @PostMapping("/saveRecord")
    public boolean saveRecord(@RequestBody Record record) {
        return recordService.dailySign(record);
    }

    @GetMapping("/signList/{method}/{uid}")
    public List<Record> signList(@PathVariable("uid") Integer uid, @PathVariable("method") Integer m) {
        if (m == 0) {
            return recordService.allToday();
        } else if (m == 1) {
            return recordService.mySignToday(uid);
        } else if (m == 2) {
            return recordService.myAbsentToday(uid);
        } else {
            return recordService.allAbsent();
        }
    }

    @PostMapping("/saveRecordAbsent")
    public boolean absentApply(@RequestBody Record record) {
        return recordService.absentApply(record);
    }

    @GetMapping("/count")
    public Count countOfRecord() {
        Count count = new Count();
        count.setCof_absent(recordService.countOfAbsent());
        count.setCof_sign(recordService.countOfSign());
        count.setCof_total(userMapper.selectCount(null));
        return count;
    }

    //数据可视化第二图
    @GetMapping("/vision/{uid}")
    public Count personalHoleRecord(@PathVariable("uid") Integer uid) {
        Count count = new Count();
        String date = DateTime.getDT("yyyy-MM");
        List<Record> list = recordMapper.personalHole(uid, date);
        int absent = 0;
        int sign = 0;
        for (Record record : list) {
            if(record.getStatus()){
                sign++;
            }else {
                absent++;
            }
        }

        count.setCof_sign(sign);
        count.setCof_absent(absent);

        return count;
    }


    @GetMapping("/salary/{uid}/{method}")
    public Integer countOFSomeOne(@PathVariable("uid") Integer uid, @PathVariable("method") Integer m) {
        if (m == 1) {
            return recordService.countOfSomeOneRecord(uid, true);
        } else {
            return recordService.countOfSomeOneRecord(uid, false);
        }
    }

    /*
    个人中心中检查签到状态使用到此接口
     */
    @GetMapping("/{uid}")
    public String personal(@PathVariable("uid") Integer uid) {
        String date = DateTime.getDT("yyyy-MM-dd");

        Record record = recordMapper.personalStatus(uid, date);

        if (record == null) {
            return "未签到";
        } else {
            if (record.getStatus()) {
                return "已签到";
            } else {
                return "请假中";
            }
        }
    }
}
