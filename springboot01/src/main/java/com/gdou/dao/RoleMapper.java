package com.gdou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface RoleMapper extends BaseMapper<Role> {

    @Select("<script>" +
            "SELECT * FROM role where username=#{usn} and password=#{pwd}" +
            "</script>")
    Role loginRole(@Param("usn") String usn, @Param("pwd") String pwd);

    @Update("<script>" +
            "UPDATE role SET author=1 where uid=#{uid}" +
            "</script>")
    boolean editRole_1(@Param("uid") Integer id);

    @Update("<script>" +
            "UPDATE role SET author=0 where uid=#{uid}" +
            "</script>")
    boolean editRole_2(@Param("uid") Integer id);

}
