package com.dmc.jsoup;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 11:24 AM 2019/2/3
 * @Modified By:
 */
public class test2 {

    public static void main(String[] args) {
        String fileName = "/Users/dingmc/tnwb/timev2";
        StringBuilder sb = readFile(fileName);
        String[] times = sb.toString().split("\n");
        List<String> date = Lists.newArrayList();
        List<String> time = Lists.newArrayList();
        List<String> devices = Lists.newArrayList();
        for (String line : times) {
            String[] fields = line.split(" ");
            String device = "";
            date.add(fields[0]);
            time.add(fields[1]);
            for (int i = 2; i < fields.length; i ++){
                if (i != 2) {
                    device += " ";
                }
                device += fields[i];
            }
            devices.add(device);
        }
        parseDate(date);
//        parseTime(time);
//        parseDevice(devices);
    }

    public static StringBuilder readFile(String pathName) {
        StringBuilder sb = new StringBuilder();
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        try (FileReader reader = new FileReader(pathName);
            BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
//                System.out.println(line);
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    public static void parseDate(List<String> times) {
        Map<String, Integer> statisticByMonth = Maps.newTreeMap();
        Map<Integer, Integer> statisticByDayOfMonth = Maps.newTreeMap();
        for (int i = 1; i <= 12; i ++) {
            statisticByMonth.put("2018-" + String.valueOf(i), 0);
            statisticByMonth.put("2019-" + String.valueOf(i), 0);
        }
        for (int i = 1; i <= 31; i ++) {
            statisticByDayOfMonth.put(i, 0);
        }
        for (String time : times) {
            String[] splits = time.split("-");
            Integer year = Integer.valueOf(splits[0]);
            Integer month = Integer.valueOf(splits[1]);
            String key = String.valueOf(year) + "-" + String.valueOf(month);
            Integer dayOfMonth = Integer.valueOf(splits[2]);
            int s1 = statisticByDayOfMonth.get(dayOfMonth) + 1;
            int s2 = statisticByMonth.get(key) + 1;
            statisticByDayOfMonth.put(dayOfMonth, s1);
            statisticByMonth.put(key, s2);
        }
        System.out.println("------按月统计------");
        statisticByMonth.entrySet().stream()
            .filter(entry -> entry.getValue() > 0)
            .map(entry -> entry.getKey() + ":" + entry.getValue())
            .peek(System.out::println).count();
//        for (int i = 1; i <= 12; i ++) {
//            System.out.println(statisticByMonth.get(i));
//        }
        System.out.println("------按天统计------");
        for (int i = 1; i <= 31; i ++) {
            System.out.println(statisticByDayOfMonth.get(i));
        }
    }

    public static void parseTime(List<String> times) {
        Map<String, Integer> statisticByTime = Maps.newHashMap();
        for (int i = 0; i < 12; i ++) {
            String str = i * 2 + "~" + (i + 1) * 2;
            statisticByTime.put(str, 0);
        }
        int cnt = 0;
        for (String time : times) {
            String hour = time.split(":")[0];
            String minute = time.split(":")[1];
            for (int i = 0; i < 12; i ++) {
                int start = i * 2;
                int end = (i + 1) * 2;
                String str = start + "~" + end;
                if (Integer.valueOf(hour) >= start && Integer.valueOf(hour) < end) {
                    statisticByTime.put(str, statisticByTime.get(str) + 1);
                    if (start == 4) {
                        System.out.println(cnt);
                    }
                }
            }
            cnt ++;
        }

        System.out.println("------按时间统计------");
        for (int i = 0; i < 12; i ++) {
            String str = i * 2 + "~" + (i + 1) * 2;
            System.out.println(statisticByTime.get(str));
        }
    }

    public static void parseDevice(List<String> devices) {
        Map<String, Integer> map = Maps.newHashMap();
        for (String device:devices) {
            if (map.get(device) == null) {
                map.put(device, 1);
            } else {
                map.put(device, map.get(device) + 1);
            }
        }
        map.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).peek(System.out::println).count();
    }
}
/**
 红包活动
 粉丝红包
 微博
 微博体育
 微博电影
 趣测君
 新浪游戏频道
 iPhone客户端
 在线求签网iqiuqian
 三星GALAXY
 生日动态
 微博会员中心
 投票
 **/