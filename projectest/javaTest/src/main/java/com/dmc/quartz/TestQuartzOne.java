package com.dmc.quartz;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
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
        log.info("MyJob  is start ..................");

        log.info("Hello quzrtz  "+
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
        System.out.println("asdas");

        log.info("MyJob  is end .....................");

    }

    public static void main(String[] args) {
        asd t = new asd("Asd");
        t.setB("fsa".getBytes());
        t.setC("fsa".getBytes());
        System.out.println(JSON.toJSONString(t));
        System.out.println(Arrays.toString(JSON.toJSONString(t).getBytes()));
    }

    @Getter
    @Setter
    static class asd {
        private String a;

        private byte[] b;

        private transient byte[] c;

        asd(String a) {
            this.a = a;
        }
    }
}
