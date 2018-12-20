package com.datetimes;


import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimesDemoMain {

    public static void main(String[] args){
        //日期
        LocalDate localDate = LocalDate.of(2018,12,19);
        //时间
        LocalTime localTime =  LocalTime.of(17,01,36,999 * 1000 * 1000);
        //时间日期1(可代替Date)
        LocalDateTime localDateTime = LocalDateTime.of(2018,12,19,17,01,36,999 * 1000 * 1000);
        //时间日期2(可代替Date)
        Instant instant = Instant.now();
        //时区(可转换为 日期 或者 时间 )
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("+08"));

        //类似SimpleDateFormat
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        //用来解析LocalDate(注意这里的格式)
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //用来解析LocalTime(注意这里的格式)
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        //解析LocalDate(必须完全匹配表达式的格式,即使后面其他内容没有用)
        LocalDate pld = LocalDate.parse("2018-12-19 00:00:00",dateTimeFormatter);
        //解析LocalTime(必须完全匹配表达式的格式,即使后面其他内容没有用)
        LocalTime plt = LocalTime.parse("2018-12-19 18:00:30",dateTimeFormatter);
        //解析LocalDateTime
        LocalDateTime pldt = LocalDateTime.parse("2000-02-02 20:20:20",dateTimeFormatter);

        //格式化LocalDate
        String dateStr  = dateFormatter.format(localDate);
        //格式化LocalTime
        String timeStr = timeFormatter.format(localTime);
        //格式化LocalDateTime
        String dateTimeStr = dateTimeFormatter.format(localDateTime);

        //LocalDateTime时间戳相互转换
        Long one = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        LocalDateTime two  = Instant.ofEpochMilli(one).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime();

        //Date转LocalDateTime
        LocalDateTime three = new Date().toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        //LocalDateTime转Date
        Date four = Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));

        System.out.println();
    }

}
