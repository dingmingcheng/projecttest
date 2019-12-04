package com.dmc.jsoup;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 4:43 PM 2019/2/26
 * @Modified By:
 */
public class test4 {

    private static Set<String> urls = Sets.newHashSet();

    public static void main(String[] args) {
        String fileName = "/Users/dingmc/Desktop/processChannel.html";
        String fileName2 = "/Users/dingmc/Desktop/parseChannel2.html";
        File file = new File(fileName);
        StringBuilder sb = test.readFile(fileName);
        Document doc = Jsoup.parse(sb.toString());
        parseDocument(doc);
        sb = test.readFile(fileName2);
        Document doc2 = Jsoup.parse(sb.toString());
        parseDocument(doc2);
        List<String> urlList = Lists.newArrayList(urls);
        for (int i = 0; i < urlList.size(); i++) {
            if ((i != 0 && i % 20 == 0) || (i == urlList.size() - 1)) {
                System.out.println();
                System.out.println(i / 20);
            }
            System.out.print(urlList.get(i));
        }
    }

    public static void parseDocument(Document document) {
        String curl = "curl -H \"Content-Type:application/json\" -X POST --data '{\"clientId\":\"{clientId}\",\"sellerId\":\"{sellerId}\",\"platform\":\"EBAY\"}' http://localhost:8010/channel/notify;";
        Elements elements = document.getElementsByTag("span");
        for (int i = 0; i < elements.size(); i += 2) {
            Element clientId = elements.get(i);
            Element sellerId = elements.get(i + 1);
            String str = curl;
            str = str.replace("{clientId}", clientId.text());
            str = str.replace("{sellerId}", sellerId.text());
            urls.add(str);
        }
        System.out.println(urls.size());
    }
}
