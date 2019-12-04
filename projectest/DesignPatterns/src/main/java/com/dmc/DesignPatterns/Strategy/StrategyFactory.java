package com.dmc.DesignPatterns.Strategy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class StrategyFactory {

    private static Map<String, Comparator> strategyList = new HashMap<>();

    static {
        Field[] fields = Student.class.getDeclaredFields();
        Method[] methods = Student.class.getMethods();
        //System.out.println("asdasd");
        for (Field field : fields) {
            field.setAccessible(true);
            strategyList.put(field.getName(), new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    try {
                        Object obj1 = field.get(o1);
                        Object obj2 = field.get(o2);
                        if (obj1 instanceof Integer) {
                            Integer fieldOne = (Integer) obj1;
                            Integer fieldTwo = (Integer) obj2;
                            if (fieldOne.compareTo(fieldTwo) >= 1) {
                                return 1;
                            } else if (fieldOne.compareTo(fieldTwo) <= -1) {
                                return -1;
                            }
                        } else if (obj1 instanceof String){
                            String fieldOne = (String) obj1;
                            String fieldTwo = (String) obj2;
                            if (fieldOne.compareTo(fieldTwo) >= 1) {
                                return 1;
                            } else if (fieldOne.compareTo(fieldTwo) <= -1) {
                                return -1;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });
        }
    }

    public static Comparator getStrategy(String strategyName) {
        return strategyList.get(strategyName);
    }
}
