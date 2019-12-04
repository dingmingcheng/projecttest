package com.dmc.jsoup;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.nlp.AipNlp;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 12:43 AM 2019/2/4
 * @Modified By:
 */
public class test3 {

    //设置APPID/AK/SK
    public static final String APP_ID = "15530598";
    public static final String API_KEY = "g2fTBIRaL66vWBFQIv2cnTB1";
    public static final String SECRET_KEY = "wP7OtXu1Y7z7GfgyAj32xtgKhBqpegGG";
    public static final AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

    static {
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }

    public static List<String> getItems(String text) {
        List<String> items = Lists.newArrayList();
        // 初始化一个AipNlp

        // 调用接口
//        String text = "据说这就是女孩子生起气来的模样。。。 哈哈哈哈哈哈哈就问你怕不怕";
        JSONObject res = client.lexer(text, null);
//        System.out.println(res.length());
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(res.toString(2));
        JSONArray array = jsonObject.getJSONArray("items");
        int size = array.size();
        for (int i = 0; i < size; i++) {
            com.alibaba.fastjson.JSONObject item = array.getJSONObject(i);
//            System.out.println(item.getString("item"));
            items.add(item.getString("item"));
        }
        return items;
    }

    public static void main(String[] args) throws Exception{
        parseOwnBolg();

    }


    public static void parseOwnBolg() throws Exception{
        String filePath = "/Users/dingmc/tnwb/otherblogcontentv2";
        List<String> contents = readFile(filePath);
        Map<String, Integer> statistic = Maps.newTreeMap();
        List<String> testCon = contents.stream().map(content -> content.split("//")[0]).filter(content -> StringUtils.isNotBlank(content)).filter(content -> !content.equals("转发微博")).collect(Collectors.toList());
        System.out.println(testCon.size());
        testCon.stream().peek(System.out::println).count();

        boolean t = false;
        if (t) {
            return;
        }
        List<Integer> failCnt = Lists.newArrayList();
        int total = testCon.size();
        for (int i = 0; i < total; i ++) {
            System.out.println("-------------" + i + "---------------");
            List<String> items = Lists.newArrayList();
            try {
                items = getItems(testCon.get(i));
            } catch (Exception e) {
                failCnt.add(i);
                Thread.sleep(500);
                continue;
            }
            int size = items.size();
            for (int j = 0; j < size; j++) {
                String word = items.get(j);
                if (StringUtils.isBlank(word)) {
                    continue;
                }
                if (statistic.get(word) == null) {
                    statistic.put(word, 1);
                } else {
                    statistic.put(word, statistic.get(word) + 1);
                }
            }
            Thread.sleep(250L);
        }
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(statistic.entrySet());
        Collections.sort(list, new Comparator<Entry<String,Integer>>() {
            //升序排序
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

//        Iterator titer=statistic.entrySet().iterator();
//        while(titer.hasNext()){
//            Map.Entry ent=(Map.Entry )titer.next();
//            String key=ent.getKey().toString();
//            String value=ent.getValue().toString();
//            System.out.println(key + ": " + value);
//        }
        int limit = list.size();
        for (int i = 0; i < limit; i ++) {
            System.out.println(list.get(i));
        }

        System.out.println(failCnt);
    }

    public static List<String> readFile(String pathName) {
        StringBuilder sb = new StringBuilder();
        List<String> t1 = Lists.newArrayList();
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
                t1.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t1;
    }

}
