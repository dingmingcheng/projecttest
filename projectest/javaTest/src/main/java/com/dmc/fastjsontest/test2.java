package com.dmc.fastjsontest;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Test;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 下午5:41 2018/5/28
 * @Modified By:
 */
public class test2 {

    @Test
    public void testSpace() {
        Map<String, String> test = JSONObject.parseObject(null, new TypeReference<Map<String, String>>() {
        });
        System.out.println(test.get("ad"));
    }

    @Test
    public void testadfa() throws Exception{
        String adf = "a;sdas";
        adf = ";asdada";
        String[] afd = new String[1];
        afd[0] = ";asda";
        afd[0] = "gfssv";

        List<Integer> a = Lists.newArrayList();
        a.add(123);
        a.stream().forEach(t -> {
//            adf = "123123";
            afd[0] = "asd";
        });
//        System.out.println(afd[0]);
        Class t = Class.forName("java.util.stream.Stream");
        Method[] methods = t.getMethods();
        long ans = Arrays.stream(methods).peek(method -> {System.out.println(method.getName());}).count();
        System.out.println(ans);
    }

}
/*
allMatch
anyMatch
builder
close
collect
concat
count
distinct
empty
filter
findAny
findFirst
flatMap
flatMapToDouble
flatMapToInt
flatMapToLong
forEach
forEachOrdered
generate
isParallel
iterate
iterator
limit
map
mapToDouble
mapToInt
mapToLong
max
min
noneMatch
of
onClose
parallel
peek
reduce
sequential
skip
sorted
spliterator
toArray
unordered
 */
