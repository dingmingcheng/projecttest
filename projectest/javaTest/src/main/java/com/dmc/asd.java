package com.dmc;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 10:11 AM 2019/4/23
 * @Modified By:
 */
public class asd {

    public static void main(String[] args) throws Exception{
        List<String> fileName = Lists.newArrayList();
        List<String> sellerId = Lists.newArrayList();

//        fileName.add("/Users/dingmc/Desktop/google/kaishihui@126.com.xls");
//        fileName.add("/Users/dingmc/Desktop/google/htrc11@hotmail.com_1.xls");
//        fileName.add("/Users/dingmc/Desktop/google/13163700023@sina.cn.xls");
//        fileName.add("/Users/dingmc/Desktop/google/marcbale@163.com.xls");
//        sellerId.add("kaishihui@126.com");
//        sellerId.add("htrc11@hotmail.com");
        getList(fileName, sellerId);
//        fileName.add("/Users/dingmc/Desktop/google/cn1514174811.xls");
//        fileName.add("/Users/dingmc/Desktop/google/速/cn1511517429.xls");
//        fileName.add("/Users/dingmc/Desktop/google/速/cn1512358019.xls");
//        fileName.add("/Users/dingmc/Desktop/google/速/cn1524520057umay.xls");
//        fileName.add("/Users/dingmc/Desktop/google/速/cn1524645767ipgq.xls");
//
//        sellerId.add("cn1004441102");
//        sellerId.add("cn1514174811");
//        sellerId.add("cn1512358019");
//        sellerId.add("cn1524520057umay");
//        sellerId.add("cn1524645767ipgq");
        int length = fileName.size();
        for (int i = 0; i < length; i++) {
            try {
                if (!fileName.get(i).contains("xls")) {
                    continue;
                }
//                System.out.println(fileName.get(i) + ";" + sellerId.get(i));
                System.out.println(i);
                ali0304(fileName.get(i), sellerId.get(i), i + 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void ali0304(String fileName, String sellerId, int i) throws Exception{
        String pre = toMkdir();
        String target = pre + DateTime.now().toString("yyyy_MM_dd_") + String.valueOf(i) + "_" + sellerId + ".xlsx";
        FileInputStream fileInputStream = new FileInputStream(new File(fileName));
        List<Map<String, Object>> ans = ExcelUtil.excelToListHasHeader(fileInputStream, "xls");
        List<Map<String, String>> tmp = Lists.newArrayList();
        ans.stream().forEach(entry ->{
            Map<String, String> test = Maps.newHashMap();
            entry.entrySet().stream().forEach(entry1 -> {
                if (entry1.getKey().equalsIgnoreCase("订单状态")) {
                    if (!String.valueOf(entry1.getValue()).equalsIgnoreCase("订单结束")) {
                        test.put("flag", "0");
                    } else {
                        test.put(entry1.getKey(), String.valueOf(entry1.getValue()));
                    }
                } else {
                    test.put(entry1.getKey(), String.valueOf(entry1.getValue()));
                }
            });
            test.put("Seller ID", sellerId);
            if (test.get("flag") == null) {
                tmp.add(test);
            }
        });
        List<String> header = Lists.newArrayList();
        header.add("Seller ID");
        header.add("订单号");
        header.add("订单状态");
        header.add("负责人(业务员)");
        header.add("买家名称");
        header.add("下单时间");
        header.add("付款时间");
        header.add("产品总金额");
        header.add("物流费用");
        header.add("订单金额");
        header.add("满立减");
        header.add("产品信息\n" + "（双击单元格展开所有产品信息！）");
        header.add("订单备注");
        header.add("收货地址");
        header.add("收货人名称");
        header.add("收货国家");
        header.add("州/省");
        header.add("城市");
        header.add("地址");
        header.add("邮编");
        header.add("联系电话");
        header.add("手机");
        header.add("买家选择物流");
        header.add("发货期限");
        header.add("实际发货物流:运单号");
        header.add("发货时间");
        header.add("确认收货时间");

        checkFile();
        ExcelUtil.generateXML(header, tmp, target);
        fileInputStream.close();
    }

    public static String toMkdir() {
        String time = DateTime.now().toString("MMdd_HH_mm");
        String file = "/Users/dingmc/Desktop/aliexpress/" + time + "/";
        File file1 = new File(file);
        file1.mkdir();
        return file;
    }

    public static void checkFile() {

    }

    public static void getList(List<String> fileNames, List<String> sellerIds) throws Exception{
        String fileName = "/Users/dingmc/Desktop/google/订单(2)/";
        File file = new File(fileName);
        File[] file1 = file.listFiles();
        for (File file2 : file1) {
            if (file2.isDirectory()) {
                for (File file3 : file2.listFiles()) {
                    String t = file3.getName();
                    t = t.replace(".xls", "");
                    String[] z = t.split("_");
                    sellerIds.add(z[0].trim());
                    fileNames.add(fileName + file2.getName() + "/" + file3.getName());
                }
            } else {
                String t = file2.getName();
                t = t.replace(".xls", "");
//                System.out.println("cn1522262778jqih");
                String[] z = t.split("_");
                sellerIds.add(z[0].trim());
                fileNames.add(fileName + file2.getName());
            }
        }
    }
}
