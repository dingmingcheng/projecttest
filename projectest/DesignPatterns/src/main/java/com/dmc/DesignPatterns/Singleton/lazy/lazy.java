package com.dmc.DesignPatterns.Singleton.lazy;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午9:10 2018/2/13
 * @Modifyed By:
 */
public class lazy {
    private static volatile lazy instance;

    private lazy() {

    }

    public static lazy newInstance() {
        if (null == instance) {
            synchronized (lazy.class) {
                if (null == instance) {
                    instance = new lazy();
                }
            }
        }
        return instance;
    }
}
