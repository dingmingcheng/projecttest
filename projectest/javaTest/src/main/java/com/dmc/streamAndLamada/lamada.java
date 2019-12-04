package com.dmc.streamAndLamada;

import com.dmc.asd;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 下午7:25 2018/6/29
 * @Modified By:
 */
public class lamada {
    static interface Intasd {
        boolean test(Integer value);
    }

    static class sdf {
//        public static void check(Intasd a) {
//            System.out.println(Intasd.class);
//            System.out.println(a.test(123));
//        }
        private Integer value;

        public sdf(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return this.value;
        }

        public Integer supply(Supplier<Integer> supplier) {
            return supplier.get();
        }


        public void consume(Consumer<sdf> consumer) {
            consumer.andThen(z -> System.out.println(z.getValue() + "===")).accept(this);
//            consumer.accept(this);
        }

        public Integer mapToValue(Function<sdf, Integer> function) {
            return function.apply(this);
//            return 1;
        }

        public void check(Predicate<Integer> predicate) {
            System.out.println(predicate.test(this.value));
        }
    }
    @Test
    public void test1() {

        sdf s = new sdf(12);
//        System.out.println(s.supply(s::getValue));
//        System.out.println(s.mapToValue(x -> (x.getValue() + 12)));
        s.consume(x -> {
            int k = x.getValue();
            System.out.println(k * 2 + 1);
            x.value = 1234;
        });
//        System.out.println(s.value);
//        s.check(x -> x > 18);
//        List<sdf> z = Lists.newArrayList();
//        Function<Integer, String> fun=(z)->"hello,"+z;
//        System.out.println(fun.apply(2));
//        List<sdf> a = Lists.newArrayList();
//        a.stream().map(z -> z.getValue()).count();

//        Predicate<Integer> asd = x -> x > 5;
//        sdf.check(x -> x > 5);
//        sdf.check(x -> x > 215);
//        sdf.check(x -> x > 5);
//        System.out.println(asd.test(13));
//        System.out.println(asd.negate());
    }

}
