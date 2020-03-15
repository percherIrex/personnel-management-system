package com.gdou.entity.myEntity;

import com.gdou.entity.Capital;
import lombok.Data;

@Data
public class CapitalNeed extends Capital{

    private Integer sign;
    private Integer absent;

    public CapitalNeed(Capital c){
        this.setId(c.getId());
        this.setUid(c.getUid());
        this.setName(c.getName());
        this.setPosition(c.getPosition());
        this.setWages(c.getWages());
        this.setFund(c.getFund());
        this.setInsure1(c.getInsure1());
        this.setInsure2(c.getInsure2());
        this.setInsure3(c.getInsure3());
        this.setInsure4(c.getInsure4());
        this.setInsure5(c.getInsure5());
    }
}
