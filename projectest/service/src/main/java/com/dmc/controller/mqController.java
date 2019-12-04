package com.dmc.controller;

import com.dmc.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mq")
public class mqController {

    @Autowired
    MqService mqService;

    @RequestMapping("/test1/{msg}")
    @ResponseBody
    public String test(@PathVariable("msg") String msg) {
        String result = mqService.send(msg);
        return result;
    }

    @RequestMapping("/test2/{msg}")
    @ResponseBody
    public String testAsync(@PathVariable("msg") String msg) {
        String result = mqService.asyncSend(msg);
        return result;
    }
}
