package com.dmc.test;

import com.sun.tools.javah.Gen;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 上午9:45 2018/2/8
 * @Modifyed By:
 */
public class common {
    static class Genn<T> {
        private T t;

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }
    }

    public static void showKey(Genn<? extends Number> obj) {
        System.out.println(obj.getT());
    }

    public static void changeKey(Genn<?> obj) {
        System.out.println(obj.getT());
    }

    public static void ttt(List<Integer> asd) {
        Integer t = asd.get(0);
        t = new Integer(12344);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(new Integer(123));
        ttt(list);
        System.out.println(list.get(0));

        Genn<Integer> g1 = new Genn<Integer>();
        Genn<Number> g2 = new Genn<Number>();
        Genn<String> g3 = new Genn<String>();
        g1.setT(5);
        g2.setT(6);
        g3.setT("asdf");
        System.out.println(g1.getT());
        System.out.println(g2.getT());
        System.out.println(g3.getT());
        System.out.println("-----------------");
        showKey(g1);
        showKey(g2);
        System.out.println("-----------------");
        changeKey(g1);
        changeKey(g2);
        System.out.println("-----------------");
        System.out.println(g1.getT());
        System.out.println(g2.getT());
    }
}
