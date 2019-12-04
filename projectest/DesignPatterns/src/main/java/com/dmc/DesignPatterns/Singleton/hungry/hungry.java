package com.dmc.DesignPatterns.Singleton.hungry;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午9:09 2018/2/13
 * @Modifyed By:
 */
public class hungry {
    private static hungry instance = new hungry();

    private hungry() { }

    public static hungry newInstance() {
        return instance;
    }
}
