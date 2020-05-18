package com.gdou.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Application {

    @TableId(value = "applicationId",type = IdType.AUTO)
    private Integer applicationId;
    private Integer uid;
    private String name;
    private String reason;
    private String date;
    private String approver;
    private Boolean handle;
    private Boolean allow;
}
