package com.gdou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.entity.Record;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RecordMapper extends BaseMapper<Record> {


    @Select("<script>" +
            "SELECT * FROM record where date=#{date} and uid=#{uid} and status=#{status}" +
            "</script>")
    Record exist(@Param("date") String date, @Param("uid") Integer uid,@Param("status") Boolean type);

    @Update("<script>" +
            "UPDATE record SET status=#{status},reason=#{reason} " +
            "WHERE date=#{date} and uid=#{uid}" +
            "</script>")
    boolean changeStatus(@Param("status") Boolean changeType,@Param("date") String date,
                         @Param("uid") Integer uid,@Param("reason") String reason);


    //当日签到的人
    @Select("<script>" +
            "SELECT * FROM record where date=#{date} and status=1" +
            "</script>")
    List<Record> allToday(@Param("date") String date);

    //我的历史请假
    @Select("<script>" +
            "SELECT * FROM record where uid=#{uid} and status=1" +
            "</script>")
    List<Record> mySign(@Param("uid") Integer uid);

    //当日请假的人**
    @Select("<script>" +
            "SELECT * FROM record where date=#{date} and status=0" +
            "</script>")
    List<Record> allTodayAbsent(@Param("date") String date);

    //我的历史请假
    @Select("<script>" +
            "SELECT * FROM record where uid=#{uid} and status=0" +
            "</script>")
    List<Record> myAbsent(@Param("uid") Integer uid);

    //当日签到的人数
    @Select("<script>" +
            "SELECT count(*) FROM record where date=#{date} and status=1" +
            "</script>")
    Integer countOfSign(@Param("date") String date);

    //当日请假的人数
    @Select("<script>" +
            "SELECT count(*) FROM record where date=#{date} and status=0" +
            "</script>")
    Integer countOfAbsent(@Param("date") String date);


    /**
     * 报表用接口
     */
    @Select("<script>" +
            "SELECT count(*) FROM record WHERE date LIKE '%${date}%' " +
            "AND uid=#{uid} AND status=#{status}</script>")
    Integer countOfSomeOneRecord(@Param("date") String date, @Param("uid") Integer uid,@Param("status") Boolean type);

}
