package com.dmc;

import java.util.concurrent.Callable;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午3:20 2018/2/1
 * @Modifyed By:
 */
public class test {

    private static final double ZSKIPLIST_P = 0.25;

    private static final int ZSKIPLIST_MAXLEVEL = 1 << 31;


    static int zslRandomLevel() {
        int level = 1;
        while (((int)(Math.random() * ZSKIPLIST_MAXLEVEL)&0xFFFF) < (ZSKIPLIST_P * 0xFFFF))
            level += 1;
        return (level<ZSKIPLIST_MAXLEVEL) ? level : ZSKIPLIST_MAXLEVEL;
    }

    public static void main(String[] args) {
        System.out.println(0xFFFF);
        for (int i = 0; i < 100; i ++) {
            System.out.println(Math.random());
        }
    }
}
