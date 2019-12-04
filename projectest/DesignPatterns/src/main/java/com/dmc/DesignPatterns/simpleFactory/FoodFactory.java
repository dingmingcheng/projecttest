package com.dmc.DesignPatterns.simpleFactory;

import java.util.concurrent.ConcurrentHashMap;

public class FoodFactory {

    private ConcurrentHashMap<String, Class> classCache;

    FoodFactory() {
        classCache = new ConcurrentHashMap<>();
    }

    public Object getClz(String clzName) throws Exception {
        Object obj;
        if (classCache.get(clzName) != null) {
            obj = classCache.get(clzName).newInstance();
        } else {
            obj = Class.forName(clzName).newInstance();
        }
        return obj;
    }
}
