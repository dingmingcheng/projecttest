package com.dmc.DesignPatterns.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 7:24 PM 2019/4/10
 * @Modified By:
 */
public class test1 {

    public static void main(String[] args) throws Exception{
//        processSHOPEE();
//        moveFile();
//        processSHOPEEdeclare();
//        statisticAmount();
    }

    public static void processSHOPEEdeclare() throws Exception{
        final String declareUrl = "curl 'http://localhost:8010/command/hbase/declare/log/update/status?platform=SHOPEE&payChannel=Fuiou&packageId={packageId}&declareReferId={declareReferId}&status=Success';";
        File file = new File("/Users/dingmc/Desktop/0410fuiou/1200.log");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        int cnt = 0;
        while ((line = bufferedReader.readLine()) != null) {
            String[] param = line.split("\t");
            //{packageId} {declareReferId}
            String url = declareUrl;
            if (cnt++ % 100 == 0) {
                System.out.println("sleep 5s;");
            }
            System.out.println(url.replace("{packageId}", param[0]).replace("{declareReferId}", param[1]));
//            System.out.println(param[0]);
//            System.out.println(param[1]);
        }

    }

    public static void processSHOPEE() {
        String url = "curl 'http://localhost:8010/command/hbase/exchange/log/update/status?platform=SHOPEE&payChannel=Fuiou&packageId={packageId}&status=Success';";
        String exUrl = "curl 'http://localhost:8010/command/excel/build?platform=SHOPEE&payChannel=Fuiou&packageId={packageId}';";
        String declareId = "curl 'http://localhost:8010/command/hbase/declare/log/update/status?platform=SHOPEE&payChannel=Fuiou&packageId={packageId}&declareReferId={declareReferId}&status=Success';";
        String hbase = "get 'exchange_log','{rowKey}'";
        String check = "ls | grep {packageId};";
        String packids = "64586\n"
            + "65164\n"
            + "65238\n"
            + "66052\n"
            + "66980\n"
            + "67281\n"
            + "67617\n"
            + "67652\n"
            + "67778\n"
            + "68040\n"
            + "68319\n"
            + "68559\n"
            + "68578\n"
            + "69080\n"
            + "70017\n"
            + "70320\n"
            + "70594\n"
            + "70716\n"
            + "70816\n"
            + "71300\n"
            + "71443\n"
            + "71929\n"
            + "72536\n"
            + "72919\n"
            + "73545\n"
            + "74031\n"
            + "74292\n"
            + "74816\n"
            + "75298\n"
            + "76131\n"
            + "76584\n"
            + "77535";
        String[] at = packids.split("\n");
        List<String> packageIds = Arrays.asList(at);
        List<String> successUrl = new ArrayList<>();
        List<String> rowKeys = new ArrayList<>();
        List<String> excelUrls = new ArrayList<>();
        List<String> checkUrls = new ArrayList<>();
        List<String> declareUrls = new ArrayList<>();
        for (String t : at) {
            String url1 = url;
            String hbaseget = hbase;
            String excelUrl = exUrl;
            String checkUrl = check;
            successUrl.add(url1.replace("{packageId}", t));
            excelUrls.add(excelUrl.replace("{packageId}", t));
            rowKeys.add(t + ":Fuiou:SHOPEE");
            checkUrls.add(checkUrl.replace("{packageId}", t));
//            System.out.println(hbaseget.replace("{rowKey}", rowKey));
        }
//        successUrl.stream().peek(System.out::println).count();
//        excelUrls.stream().peek(System.out::println).count();
//        checkUrls.stream().peek(System.out::println).count();
        String target = packageIds.stream().map(id -> "'"+ id +"'").collect(Collectors.joining(","));
        System.out.println(target);
    }

    public static void moveFile() {
        String packids = "64586\n"
            + "65164\n"
            + "65238\n"
            + "66052\n"
            + "66980\n"
            + "67281\n"
            + "67617\n"
            + "67652\n"
            + "67778\n"
            + "68040\n"
            + "68319\n"
            + "68559\n"
            + "68578\n"
            + "69080\n"
            + "70017\n"
            + "70320\n"
            + "70594\n"
            + "70716\n"
            + "70816\n"
            + "71300\n"
            + "71443\n"
            + "71929\n"
            + "72536\n"
            + "72919\n"
            + "73545\n"
            + "74031\n"
            + "74292\n"
            + "74816\n"
            + "75298\n"
            + "76131\n"
            + "76584\n"
            + "77535";
        String sourceDir = "/Users/dingmc/Desktop/0410fuiou/Fuiou/";
        String targetDir = "/Users/dingmc/Desktop/0410fuiou/Fuiou/tmp/";
        String[] at = packids.split("\n");
//        File file1 = new File(sourceDir + "68319_Fuiou_SHOPEE.xlsx");
//        File file2 = new File(targetDir + "68319_Fuiou_SHOPEE.xlsx");
//        file1.renameTo(file2);
        List<File> files = new ArrayList<>();
        for (String a : at) {
            File file = new File(sourceDir + a + "_Fuiou_SHOPEE.xlsx");
            File target = new File(targetDir + a + "_Fuiou_SHOPEE.xlsx");
            if (file.exists()) {
//                file.renameTo(target);
//                System.out.println(file.getName());
            } else {
                System.out.println(file.getName() + " is not exist");
            }
        }
    }
}
