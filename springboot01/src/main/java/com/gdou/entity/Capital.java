package com.gdou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "capital")
@Data
public class Capital {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer uid;
    private String name;
    private String position;
    private Float wages;
    private Float fund;
    private Float insure1;
    private Float insure2;
    private Float insure3;
    private Float insure4;
    private Float insure5;
}
