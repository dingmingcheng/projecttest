package com.dmc.controller;

import com.dmc.dao.TesttableMapper;
import com.dmc.model.TesttableEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.dmc.service.Impl.mockInfoServiceImpl;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 上午9:21 2018/1/9
 * @Modifyed By:
 */

public class okControllerTest extends BaseTestService{
    @Autowired
    private okController okController;

    @Autowired
    private mockInfoServiceImpl mockInfoService;

    @Autowired
    private TesttableMapper testtableMapper;

    public static ConcurrentHashMap<String, Class> cache = new ConcurrentHashMap<>();
    @Test
    public void testFunction() {
        TesttableEntity entity = new TesttableEntity();
        entity.setNum(123);
        entity.setName("asd");
        testtableMapper.insert(entity);
        System.out.println("asdsa");
    }

    public static void main(String[] args) throws Exception {
        final int TIMES = 1000;
        int time = TIMES;
        long startTime = System.nanoTime();
        while ((time--) >= 0) {
            Object ans = getInstance("com.dmc.controller.okController").newInstance();
        }
        long endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        long startTime1 = System.nanoTime();
        while ((time--) >= 0) {
            com.dmc.controller.okController test = new okController();
        }
        long endTime1 = System.nanoTime();
        System.out.println(endTime1 - startTime1);

    }

    public static Class getInstance(String className) throws Exception{
        Class clz;
        if (cache.get(className) != null) {
            clz = cache.get(className);
        } else {
            clz = Class.forName(className);
            cache.put(className, clz);
        }
        return clz;
    }
}
