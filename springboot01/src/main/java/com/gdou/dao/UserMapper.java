package com.gdou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
    继承mybatisplus的接口可快速使用CRUD，不需要写映射文件
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("<script>" +
            "SELECT * FROM myTemp.user where concat(`name`,`gender`,`depart`,`position`,`address`) like '%${keyWord}%'" +
            "</script>")
    List<User> findByKey(@Param("keyWord") String key);



}
