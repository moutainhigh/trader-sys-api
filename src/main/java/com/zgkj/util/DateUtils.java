package com.zgkj.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    public static String getDateBefore(int beforeNum){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        now = now.minus(beforeNum, ChronoUnit.DAYS);
        return formatter.format(now);
    }
    public static String getDateNow(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }
    public static String getTimeNow(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }
    public static String getTimeBefore(int beforeNum){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        now = now.minus(beforeNum, ChronoUnit.DAYS);
        return formatter.format(now);
    }
    public static String getGMTDateTimeLater(Integer days){
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(timeZone);
        return LocalDateTime.parse(simpleDateFormat.format(new Date()), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).plusDays(days)+"GMT+08:00";
    }
    public static String getSystemNumber(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSss");
        String str = df.format(new Date());
        return str;
    }
}
