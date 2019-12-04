package com.dmc.controller;

import com.dmc.service.Impl.mockInfoServiceImpl;
import com.dmc.service.transferMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午2:15 2018/2/8
 * @Modifyed By:
 */
@Controller
@RequestMapping("/trans")
public class springTransTestController {

    @Autowired
    mockInfoServiceImpl mockInfoService;

    @Autowired
    transferMoneyService transferMoneyService;

    @ResponseBody
    @RequestMapping("/spread")
    public String spreadTest() {
        return "ok";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String testTrans() {
        mockInfoService.testTrans();
        return "ok";
    }
}
