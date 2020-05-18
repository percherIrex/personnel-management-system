package com.gdou.MyUitls;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    //格式化时间
    public static String getDT(String pattern){
        return new SimpleDateFormat(pattern).format(new Date());
    }
}
