package com.gdou.MyUitls;

import com.gdou.entity.Operation;

public class OperationRecord {
    public static Operation init(String who,String operate,String what){
        Operation operation = new Operation();
        operation.setWho(who);
        operation.setOperate(operate);
        operation.setWhat(what);
        operation.setDate(DateTime.getDT("yyyy-MM-dd"));
        operation.setTime(DateTime.getDT("HH:mm:ss"));
        return operation;
    }
}
