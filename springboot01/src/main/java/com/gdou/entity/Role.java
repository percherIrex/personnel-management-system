package com.gdou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@TableName(value = "role")
@Data
public class Role {

    @TableId(value = "rid", type = IdType.AUTO)
    @JsonIgnore
    private Integer rid;
    private String name;
    private Integer uid;
    private Boolean author;
    private String username;
}
