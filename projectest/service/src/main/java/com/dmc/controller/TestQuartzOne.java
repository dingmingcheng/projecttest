package com.dmc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 下午4:13 2018/7/21
 * @Modified By:
 */
@Slf4j
@Component
public class TestQuartzOne {

    @Scheduled(fixedRate = 3000)
    public void execute() {
        log.info("MyJob1  is start ..................");

        log.info("Hello quzrtz  "+
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
        System.out.println("asdas");

        log.info("MyJob  is end .....................");

    }
}
