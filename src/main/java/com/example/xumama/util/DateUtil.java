package com.example.xumama.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * DateUtil
 *
 * @author zhangshun
 * @date 2022/8/10
 */
public class DateUtil {

    /**
     * 获取今日日期
     * @return 今日日期 0点0分0秒
     * @author zhangShun 2022/8/10
     */
    public static Date getDate(){
        //获取今日日期
        LocalDate date = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(date, LocalTime.of(0,0,0));
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
