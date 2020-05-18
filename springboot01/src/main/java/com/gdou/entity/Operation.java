package com.gdou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Operation {
    @TableId(value = "history_id",type = IdType.AUTO)
    private Integer history_id;
    private String who;
    private String operate;
    private String what;
    private String date;
    private String time;
}
