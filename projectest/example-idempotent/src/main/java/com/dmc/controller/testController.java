package com.dmc.controller;

import com.dmc.service.transferMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 上午9:37 2017/12/29
 * @Modifyed By:
 */
@Controller
@RequestMapping("/idempotent")
public class testController {

    @Autowired
    transferMoneyService transferMoneyService;

    @RequestMapping("/none")
    @ResponseBody
    public String test0() {

        String id = getOrderIdByUUId();
        System.out.println(id);
        Long startTime = System.currentTimeMillis();
        String ans = transferMoneyService.none(1L, 500);
        Long endTime = System.currentTimeMillis();

        //模拟网络延迟
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ans;
    }

    @RequestMapping("/optimistic")
    @ResponseBody
    public String test1() {
        transferMoneyService.optimistic(1L, 500);
        return "ok";
    }

    @RequestMapping("/redis")
    @ResponseBody
    public String test2() {
        return "ok";
    }

    @RequestMapping("/index")
    @ResponseBody
    public String test3() {
        return "ok";
    }

    public static String getOrderIdByUUId() {
        Date date=new Date();
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String time = format.format(date);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return time + String.format("%011d", hashCodeV);
    }

    public static void main(String[] args) {
        System.exit(2);
    }
}
