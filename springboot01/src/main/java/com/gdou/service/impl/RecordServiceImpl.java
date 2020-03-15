package com.gdou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gdou.dao.RecordMapper;
import com.gdou.entity.Record;
import com.gdou.service.RecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @Resource
    private RecordMapper recordMapper;

    @Override
    public boolean dailySign(Record r) {

        Date d = new Date();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(d);
        String time = new SimpleDateFormat("HH:mm:ss").format(d);

        //打卡函数，一人一日只打卡一次
        Integer uid = r.getUid();

        Record record = recordMapper.exist(date, uid, true);
        if (record != null) {
            return false;
        } else {
            //此人是否原定今日请假
            record = recordMapper.exist(date, uid, false);
            if (record != null) {
                //把请假的状态改为上班
                return recordMapper.changeStatus(true, date, uid, time);
            } else {
                record = r;
                record.setDate(date);
                record.setReason(time);
                recordMapper.insert(record);
                return true;
            }
        }

    }

    @Override
    public List<Record> allToday() {
        Date d = new Date();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(d);
        return recordMapper.allToday(date);
    }

    @Override
    public List<Record> mySignToday(Integer uid) {
        return recordMapper.mySign(uid);
    }

    @Override
    public List<Record> allAbsent() {
        Date d = new Date();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(d);
        return recordMapper.allTodayAbsent(date);
    }

    @Override
    public List<Record> myAbsentToday(Integer uid) {
        return recordMapper.myAbsent(uid);
    }

    /**
     * 仅使已签到的人当日请假
     *
     * @param r
     * @return
     */
    @Override
    public boolean absentApply(Record r) {
        Date d = new Date();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(d);

        Integer uid = r.getUid();
        String reason = r.getReason();

        Record record = recordMapper.exist(date, uid, true);
        if (record != null) {
            return recordMapper.changeStatus(false, date, uid, reason);
        } else {
            return false;
        }

    }

    @Override
    public Integer countOfSign() {
        Date d = new Date();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(d);
        return recordMapper.countOfSign(date);
    }

    @Override
    public Integer countOfAbsent() {
        Date d = new Date();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(d);
        return recordMapper.countOfAbsent(date);
    }

    /**
     * 某人当月请假和打卡数  用于工资计算
     * @param uid
     * @param type
     * @return
     */
    @Override
    public Integer countOfSomeOneRecord(Integer uid, Boolean type) {
        Date d = new Date();
        String date = new SimpleDateFormat("yyyy-MM").format(d);

        return recordMapper.countOfSomeOneRecord(date,uid,type);
    }


}
