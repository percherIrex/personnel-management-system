package com.gdou.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gdou.entity.Record;

import java.util.List;

public interface RecordService extends IService<Record> {

    boolean dailySign(Record record);

    List<Record> allToday();

    List<Record> mySignToday(Integer uid);

    List<Record> allAbsent();

    List<Record> myAbsentToday(Integer uid);

    boolean absentApply(Record record);

    Integer countOfSign();

    Integer countOfAbsent();

    Integer countOfSomeOneRecord(Integer uid,Boolean type);
}
