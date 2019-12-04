package com.dmc.streamAndLamada;

import com.dmc.streamAndLamada.bean.Apple;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 下午9:01 2018/7/9
 * @Modified By:
 */
public class streamTest {
    public static List<Integer> list = Lists.newArrayList();

    public static List<Apple> apples = Lists.newArrayList();

    static {
        apples.add(new Apple(1,"苹果1",new BigDecimal("-3.25"),10));
        apples.add(new Apple(1,"苹果2",new BigDecimal("1.35"),20));
        apples.add(new Apple(2,"香蕉",new BigDecimal("2.89"),30));
        apples.add(new Apple(3,"荔枝",new BigDecimal("9.99"),40));
    }

    @Test
    public void t1() {

        Stream.of(9,2,3,4,5).forEachOrdered(System.out::println);
        System.out.println(Stream.of(9,2,3,4,5).allMatch(i -> i > 3));
        System.out.println(Stream.of(9,2,3,4,5).count());
        System.out.println(Stream.of(9,2,3,4,5).sorted(Integer::compareTo));
        Stream.of(9,2,3,6,5).sorted((s1, s2) -> {
            if (s1 >= s2) {
                return -1;
            } else {
                return 1;
            }
        }).peek(System.out::println).count();

        System.out.println("--------------");
        Optional<Integer> x = Stream.of(9,2,3,4,5).min((s1, s2) -> {
            if (s1 >= s2) {
                return -1;
            } else {
                return 1;
            }
        });
        System.out.println(x.get());

        Stream.concat(Stream.of(1,2,4,3), Stream.of("f", 4, 5)).peek(System.out::println).count();
        System.out.println("--------------");
        System.out.println(Stream.of(1, 9, 0, 12, 3).findAny().get());
        System.out.println(Stream.of(1, 9, 0, 12, 3).parallel().isParallel());

    }

    @Test
    public void testCollect() {
//        apples.stream().collect(Collectors.toMap(Function.identity(), Collectors.));

        Map<Boolean, List<Apple>> ans = apples.stream().collect(Collectors.partitioningBy(s -> s.getNum() > 25));

        ans.get(true).stream().peek(System.out::println).count();

    }

    @Test
    public void testReduce() {
//        apples.stream().reduce()
        IntStream.range(0, 11).peek(System.out::println).count();
        List<BigDecimal> z = Lists.newArrayList();
        z.add(new BigDecimal("2.3"));
        z.add(new BigDecimal("2.4"));
        z.add(new BigDecimal("2.5"));
        System.out.println(z.stream().reduce(BigDecimal::add).get());
        Spliterator<BigDecimal> spliterator = Spliterators.spliterator(z, 0);
        System.out.println("asfsdf");
    }

    @Test
    public void testBinaryOp() {

    }
}

/*
base:http://colobu.com/2016/03/02/Java-Stream/#sorted
collect,reduce:https://www.cnblogs.com/CarpenterLee/p/6550212.html
allMatch    jz,predicate均满足return true
anyMatch    jz,任意满足true
noneMatch   jz,全不满足true
concat  static,按顺序连接两个流
count   jz,求元素总和
distinct    dx,时间复杂度的问题todo
empty   static，返回空stream
filter  dx,过滤，predicate
findAny jz,返回任意一个元素
findFirst jz,返回第一个元素
flatMap dx
flatMapToDouble dx
flatMapToInt    dx
flatMapToLong   dx
forEach jz
forEachOrdered  jz,encounter order?
limit   dx,取前几个
map dx
mapToDouble dx
mapToInt    dx
mapToLong   dx
max jz,Optional,返回最后一个元素
min jz,Optional,返回第一个元素
sorted  dx,Comparator
peek    dx,Consumer,均采取操作
parallel    转并行流
sequential  转串行流
isParallel  是否是并行流
skip    dx,丢弃前nge
toArray jz,返回数组
{reduce,collect均存在一个包含三个参数的函数，代表并行时，多个并行部分的合并方式}
*reduce 生成单个值，BinaryOperator,包含max,min,count,sum
*collect    Collectors类比较重要，包括groupBy，join
Collectors
1.toMap
2.partitioningBy    分成两部分
3.groupingBy
4.joining
generate
spliterator
builder
unordered
 */
