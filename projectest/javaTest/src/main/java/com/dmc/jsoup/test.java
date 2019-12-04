package com.dmc.jsoup;

import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 8:03 PM 2019/1/30
 * @Modified By:
 */
public class test {

    public static Set<String> devices = Sets.newHashSet();

    public static void main(String[] args) {
        String prefix = "/Users/dingmc/tnwb/2019_";
        int month = 1;
        int index = 1;
        while (true) {
            String monthStr = String.format("%02d", month);
            String indexStr = String.format("%02d", index);
            String fileName = prefix + monthStr + "_" + indexStr;
            File file = new File(fileName);
            boolean flag = file.exists();
            if (flag) {
//                System.out.println(fileName);
                StringBuilder sb = readFile(fileName);
                Document doc = Jsoup.parse(sb.toString());
                parseDocument(doc);
                index++;
            } else {
                month++;
                index = 1;
            }
            if (month == 5) {
                break;
            }
        }
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    public static void parseDocument(Document document) {
        Elements elements = document.getElementsByClass("WB_detail");
        //时间以及设备
        {

//            for (Element element : elements) {
//                //time, device
//                Elements count = element.getElementsByClass("WB_from S_txt2");
////            System.out.println(count.get(0));
//                String ans = count.get(0).text().replace("来自 ", "");
//                String[] splits = ans.split(" ");
////                System.out.println(ans);
//                for (int i = 0; i < splits.length; i++) {
//                    if (i == 0) {
//                        System.out.print("2019-");
//                        System.out.print(splits[0].substring(0, splits[0].indexOf("月")));
//                        System.out.print("-");
//                        System.out.print(splits[0].substring(splits[0].indexOf("月") + 1, splits[0].indexOf("日")));
//                        if (i < splits.length - 1) {
//                            System.out.print(" ");
//                        }
//                    } else {
//                        System.out.print(splits[i]);
//                        if (i < splits.length - 1) {
//                            System.out.print(" ");
//                        }
//                    }
//                }
//                System.out.println();
////                devices.add(splits[2]);
//            }
////            devices.stream().peek(System.out::println).count();
//
////        System.out.println(elements.get(1));
        }

/**
        //转发或者原创内容
        {
            for (Element element : elements) {
                //time, device
                Elements count = element.getElementsByClass("WB_text W_f14");
//                for (Element element1 : count) {
//                    System.out.println(element1.text());
//                }
                System.out.println(count.text());
            }
        }
*/

        //转发的原博内容
        {
            for (Element element3 : elements) {
                try {
                    Elements elements1 = element3.getElementsByClass("WB_feed_expand");
                    if (elements1.size() > 0) {
                        Elements elements2 = elements1.get(0).getElementsByClass("WB_expand S_bg1");
                        Element element4 = elements2.get(0);
//                        System.out.println(element4.getElementsByClass("W_fb S_txt1").get(0).text());
                        System.out.println(element4.getElementsByClass("WB_text").get(0).text());
//                    if (elements2.size() > 0) {
//                        System.out.println(elements2.get(0).getElementsByClass("WB_text"));
//                    }
                    }
                } catch (Exception e) {
//                    System.out.println("error....");
                }
            }
        }
    }
}

//WB_text W_f14
//WB_text W_f14

/*
0:zhuanfa:
WB_detail
  WB_from S_txt2
    S_txt2(time)
    S_txt2(device)
  WB_text W_f14(content)
    img
    a
  WB_feed_expand
    WB_expand S_bg1
      WB_info
        W_fb S_txt1(nickname)
      WB_text(origin content)

原创
WB_detail
  WB_from S_txt2
    S_txt2(time)
    S_txt2(device)
  WB_text W_f14

*/

//统计人工以及第三方应用
/*
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
 */