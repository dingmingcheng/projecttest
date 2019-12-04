package com.dmc;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
 */
public class problem1 {
    public static void main(String[] args) {
        int[] numbers = new int[]{3,323,32123};
        String str = PrintMinNumber(numbers);
        System.out.println(str);
    }
    public static String PrintMinNumber(int [] numbers) {
        Integer[] nums = new Integer[numbers.length];
        for (int i = 0; i < numbers.length; i ++) {
            nums[i] = Integer.valueOf(numbers[i]);
        }
        Arrays.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String str1 = o1 + "";
                String str2 = o2 + "";
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                return s1.compareTo(s2);
            }
        });
        //System.out.println("asdas");
        StringBuilder str = new StringBuilder();
        for (int num : nums) {
            //System.out.println(num);
            str.append(num + "");
        }
        return str.toString();
    }
}
