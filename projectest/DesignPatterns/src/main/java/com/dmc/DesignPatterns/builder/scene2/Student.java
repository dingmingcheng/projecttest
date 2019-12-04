package com.dmc.DesignPatterns.builder.scene2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

public class Student {
    private String name;
    private String id;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void main(String[] args) {
        // 当前 时间:O(n * w),空间:O(w)
        // 对a数组离散化后 时间:O(n * n) , 空间:O(n)
//        int[] a = {0, 200,300,400,2000,6000,7000};
//        int[] a = {3,2,70,100,60,31,20,4};
//        //最大值
//        int w = 89;
//        int[][] dp = new int[2][w + 1];
//        int[] flag = new int[a.length];
//        dp[0][0] = 1;
//        dp[1][0] = 1;
//        dp[]
//
//        for (int i = 1; i < a.length; i++) {
//            for (int j = 0; j <= w; j++) {
//                dp[i % 2][j] = dp[(i - 1)%2][j];
//                if (j - a[i] >= 0) {
//                    dp[i % 2][j] = Math.max(dp[(i - 1)%2][j - a[i]], dp[i%2][j]);
//                }
//            }
//        }
//        for (int i = w; i >=0 ; i--) {
//            if (dp[(a.length - 1)%2][i] != 0) {
//                System.out.println(i);
//                return;
//            }
//        }
        test();
    }

    public static void test() {
//        int[] a = {0, 200,300,400,2000,6000,7000};
        int[] a = {90,3,70,100,60,31,20,4};
        //最大值
        int w = 1;
        int ans = -0x7fffffff;

        Set<Integer> last = new HashSet<>();
        Map<Integer, Integer> flag = new HashMap<>();
        flag.put(a[0], 0);
        last.add(0);
        last.add(a[0]);
        if (a[0] <= w) {
            ans = a[0];
        }
        for (int i = 1; i < a.length; i++) {
            Set<Integer> tmp = new HashSet<>();
            for (Integer z : last) {
                if (z + a[i] <= w) {
                    ans = Math.max(ans, z + a[i]);
                    tmp.add(z + a[i]);
                    flag.put(z + a[i], z);
                }
            }
            last.addAll(tmp);
        }
        if (ans == -0x7fffffff) {
            return;
        }
        System.out.println("answer is " + ans);
        while (ans > 0) {
            System.out.println(ans - flag.get(ans));
            ans = flag.get(ans);
        }
    }

    private static void lisan(int[] origin,int[] rank, int[] match) {
        @Getter
        @Setter
        @AllArgsConstructor
        class Pair<T1, T2> {
            private T1 object1;
            private T2 object2;

            @Override
            public boolean equals(Object obj) {
                return object1.equals(((Pair)obj).getObject1()) &&
                    object2.equals(((Pair)obj).getObject2());
            }

            @Override
            public int hashCode() {
                return object1.hashCode() / 2 + object2.hashCode() / 2;
            }
        }
        @AllArgsConstructor
        @Getter
        class A{
            int value;
            int index;
        }
        int length = origin.length;
        A[] box = new A[length];
        for (int i = 0; i < length; i++) {
            box[i] = new A(origin[i], i);
        }
        Arrays.sort(box, Comparator.comparingInt(A::getValue));
        for (int i = 0; i < length; i++) {
            rank[box[i].index] = i;
            match[rank[box[i].index]] = box[i].value;
        }
        for (int i = 0; i < length; i++) {
            System.out.println(rank[i]);
            System.out.println(match[rank[i]]);
        }
    }
}
