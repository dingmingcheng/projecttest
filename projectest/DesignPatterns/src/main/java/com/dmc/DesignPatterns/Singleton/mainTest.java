package com.dmc.DesignPatterns.Singleton;

import com.dmc.DesignPatterns.Singleton.hungry.hungry;
import com.dmc.DesignPatterns.Singleton.lazy.lazy;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午9:35 2018/2/13
 * @Modifyed By:
 */
public class mainTest {
    public static void main(String[] args) {
        lazy lz = lazy.newInstance();
        hungry hrg = hungry.newInstance();
    }
}
