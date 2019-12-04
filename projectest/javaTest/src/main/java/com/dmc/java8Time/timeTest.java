package com.dmc.java8Time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 下午12:12 2018/7/14
 * @Modified By:
 */
public class timeTest {
    /**
     * Date
     * long(timestamp)
     * LocalDateTime
     * String类型的表现形式
     */
    @Test
    public void test1() {
        Date now = new Date();
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now);
        System.out.println(now1);
    }

    /**
     * long <==> localDateTime
     */
    @Test
    public void test2() {
        long millis = System.currentTimeMillis();
        System.out.println(millis);
        //long -> localDateTime
        LocalDateTime dateTime =LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), TimeZone.getDefault().toZoneId());
        System.out.println(dateTime);
        dateTime =LocalDateTime.ofInstant(Instant.ofEpochSecond(millis / 1000), TimeZone.getDefault().toZoneId());
        System.out.println(dateTime);
        dateTime =LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), TimeZone.getTimeZone("GMT+8:00").toZoneId());
        System.out.println(dateTime);
        dateTime =LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), TimeZone.getTimeZone(ZoneId.SHORT_IDS.get("CTT")).toZoneId());
        System.out.println(dateTime);
        //long <- localDateTime
        Instant ans = LocalDateTime.now().toInstant(ZoneOffset.of("+8"));
        System.out.println(ans.getEpochSecond());
        System.out.println(ans.getNano());
    }

    /**
     * String <==> localDateTime
     */
    @Test
    public void test3() {
        String time = "2003-03-03T12:04:32.123Z";
        LocalDateTime t = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        System.out.println(t);
        //localDateTime -> String
        String ans = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        System.out.println(ans);
        ans = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd日"));
        System.out.println(ans);
    }

    /**
     * long <==> Date
     */
    @Test
    public void test4() {
        long millis = System.currentTimeMillis();
        System.out.println(millis);
        //long -> Date
        DateTime dateTime = new DateTime(millis);
        System.out.println(dateTime);
        //Date -> long
        DateTime now = new DateTime(new Date());
        System.out.println(now.toDate().getTime());
    }

    /**
     * String <==> Date
     */
    @Test
    public void test5() {
        //String -> Date
        String time = "2003-03-03T12:04:32.123Z";
        DateTime dateTime1 = DateTime.parse(time, org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        System.out.println(dateTime1.toDate());
        System.out.println(dateTime1);
        //String <- Date
        DateTime dateTime = new DateTime(new Date());
        System.out.println(dateTime.withZone(DateTimeZone.forTimeZone(TimeZone.getTimeZone("GMT+8:00"))));
        System.out.println(dateTime.toString("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
    }
}
