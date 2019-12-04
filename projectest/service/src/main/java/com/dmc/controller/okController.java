package com.dmc.controller;

import com.dmc.service.TesttableService;
import com.dmc.service.UserTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dmc.service.Impl.mockInfoServiceImpl;
/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@yuntu-inc.com
 * @Description
 * @Date: Created in 下午2:39 2017/12/28
 * @Modifyed By:
 */
@Controller
@RequestMapping
public class okController {

    @Autowired
    mockInfoServiceImpl mockInfoService;

    @Autowired
    TesttableService testtableService;

    @Autowired
    UserTableService userTableService;

    @RequestMapping("/ok")
    @ResponseBody
    public String ok() {
        testtableService.tableInsert();
        return "ok";
    }

    @RequestMapping("/ok1")
    @ResponseBody
    public String ok1() throws InterruptedException {
        for (int i = 0; i <= 1000000; i ++) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        return "ok";
    }

    @RequestMapping("/ok2")
    @ResponseBody
    public String ok2() throws InterruptedException {
        userTableService.testInsert();
        return "ok";
    }

    @RequestMapping(value = "/rests/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String test1() {
        return "okGet";
    }

    @RequestMapping(value = "/rests/t1", method = RequestMethod.POST)
    @ResponseBody
    public String test2() {
        return "okPost";
    }

    @RequestMapping(value = "/rests/t1", method = RequestMethod.DELETE)
    @ResponseBody
    public String test3() {
        return "okDelete";
    }

    @RequestMapping(value = "/rests/t1", method = RequestMethod.PUT)
    @ResponseBody
    public String test4() {
        return "okPut";
    }
}
