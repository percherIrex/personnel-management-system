package com.gdou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.entity.Application;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ApplicationMapper extends BaseMapper<Application> {
    @Select("<script>" +
            "SELECT * FROM myTemp.application where uid=#{uid} and date=#{date}" +
            "</script>")
    Application is_Ex(@Param("uid") Integer uid,
                      @Param("date") String date);

    @Select("<script>" +
            "SELECT * FROM myTemp.application where uid=#{uid}" +
            "</script>")
    List<Application> listByUid(@Param("uid") Integer uid);

    @Select("<script>" +
            "SELECT * FROM myTemp.application where handle = 0 " +
            "</script>")
    List<Application> listByHandle();
}
