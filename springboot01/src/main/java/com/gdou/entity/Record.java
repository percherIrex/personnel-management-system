package com.gdou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "record")
@Data
public class Record {

    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;
    private String name;
    private Integer uid;
    private Boolean status;
    private String reason;
    private String date;
}
