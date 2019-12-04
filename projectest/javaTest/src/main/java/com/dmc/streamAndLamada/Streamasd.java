package com.dmc.streamAndLamada;

import com.dmc.fastjsontest.jsonTestBean.Simple;
import com.dmc.streamAndLamada.bean.Apple;
import com.dmc.streamAndLamada.bean.BeanA;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 下午3:46 2018/5/29
 * @Modified By:
 */
public class Streamasd {
    @Test
    public void testFilter() {
        List<Simple> list = new ArrayList<>();
        list.add(new Simple("a", "b", 1));
        list.add(new Simple("b", "b", 1));
        list.add(new Simple("c", "g", 1));
        list.add(new Simple("d", "b", 1));
        list.add(new Simple("e", "g", 1));

        List<String> ans = list.stream()
            .flatMap(item -> Stream.of(item.getName(), item.getAge().toString()))
            .collect(Collectors.toList());
        System.out.println(ans);

        Integer ans1 = list.stream().map(item -> item.getAge())
            .reduce(0, Integer::sum);
        System.out.println(ans1);
        List<Simple> result = list.parallelStream()
            .filter(item -> "b".equals(item.getSex()))
            .collect(Collectors.toList());
        result.stream().forEach(item -> {
            item.setSex("t");
        });
        for (Simple item : result) {
            System.out.println(item);
        }
    }
    @Test
    public void test1() {
        List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        System.out.println("sum is:"+nums.stream().filter(num -> num != null).
            distinct().mapToInt(num -> num * 2).
            peek(System.out::println).sum());
    }

    @Test
    public void test2() {
        List<BeanA> list = Lists.newArrayList();
        list.add(new BeanA(1, "a"));
        list.add(new BeanA(2, "b"));
        list.add(new BeanA(3, "c"));
        list.add(new BeanA(4, "d"));
        list.add(new BeanA(5, "e"));
        int sum = list.stream().mapToInt(item -> item.getNum()).sum();
        System.out.println(sum);
    }

    @Test
    public void test3() {
        List<BeanA> list = Lists.newArrayList();
        list.add(new BeanA(1, "a"));
        list.add(new BeanA(2, "b"));
        list.add(new BeanA(3, "c"));
        list.add(new BeanA(4, "d"));
        list.add(new BeanA(5, "e"));
    }

    @Test
    public void test4() {
        List<Apple> appleList = new ArrayList<>();//存放apple对象集合

        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("-3.25"),10);
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);
        List<Integer> list = appleList.parallelStream().map(Apple::getId).collect(Collectors.toList());
        //list.stream().parallel().peek(System.out::println).collect(Collectors.toList());
;        System.out.println(list);
        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("totalMoney:"+totalMoney);  //totalMoney:17.48
    }

    @Test
    public void test5() {
        Map<String, String> map = Maps.newHashMap();
        map.put("1", "1");
        map.put("2", "2");
        System.out.println(map.size());
    }

    @Test
    public void test6() {
        System.out.println(addUp(Stream.of(1, 2, 3)));
    }

    public int addUp(Stream<Integer> asd) {
        return asd.reduce(0, (a, b) -> {return a + b;});
        //return asd.reduce(1, (acc, x) -> acc + x);
    }

}
