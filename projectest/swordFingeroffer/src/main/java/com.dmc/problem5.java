package com.dmc;

/**
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class problem5 {
    public static void main(String[] args) {
        int target = 23;
        int[][] array = new int[][]{{1, 5, 8, 15},{2, 7, 10, 17}};
        boolean ans = Find(target, array);
        System.out.println(ans);
    }

    public static boolean Find(int target, int [][] array) {
        int n = 0;
        int m = array[0].length - 1;
        while (n < array.length && m >= 0) {
            if (target > array[n][m]) {
                n ++;
            } else if (target < array[n][m]) {
                m --;
            } else if (target == array[n][m]) {
                return true;
            }
        }
        return false;
    }
}































