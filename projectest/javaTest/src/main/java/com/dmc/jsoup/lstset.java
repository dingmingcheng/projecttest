package com.dmc.jsoup;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 5:47 PM 2019/4/21
 * @Modified By:
 */
public class lstset {

    public static void main(String[] args) {
//        DateTime time = DateTime.parse("2019-04-23 22:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm"));
//        DateTime time1 = DateTime.parse("2018-08-01 10:04", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm"));
//        long ans = time.toDate().getTime() - time1.toDate().getTime();
//        System.out.println(ans / 1000 / 3600 / 24);
        String s = "正如开头中提到的Aldebaran\n"
            + "我想每个人的夜空中都会有其所认为最亮的那颗星星。\n"
            + "今天是你的生日\n"
            + "还是希望你以后的日子\n"
            + "爱你所爱，行你所行，听从你心\n"
            + "更漂亮，更乐观，更善良\n"
            + "更健康，更自信，更，可，爱\n"
            + "：P";
        String[] t = s.split("\n");
        final String z = "<p class=\"txt-1\">\n"
            + "          <span>{span}</span>\n"
            + "</p>";
        for (String x : t) {
            String y = z.replace("{span}", x);
            System.out.println(y);
            System.out.println();
        }
    }
}
