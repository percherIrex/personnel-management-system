package com.gdou.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gdou.entity.Operation;
import com.gdou.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OperationMapper extends BaseMapper<Operation> {

    @Select("<script>" +
            "SELECT * FROM myTemp.operation where concat(`who`,`operate`,`what`,`date`) like '%${keyWord}%'" +
            "</script>")
    List<Operation> findByKey(@Param("keyWord") String key);
}
