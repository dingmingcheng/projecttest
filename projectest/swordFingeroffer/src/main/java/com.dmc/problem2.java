package com.dmc;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class problem2 {
    public static void main(String[] args) {
        int[] array = new int[]{1,6,9,6,6};
        int ans = MoreThanHalfNum_Solution(array);
        System.out.println(ans);
    }
    public static int MoreThanHalfNum_Solution(int [] array) {
        int ans = array[0];
        int cnt = 1;
        for (int i = 1; i < array.length; i ++) {
            if (array[i] == ans) {
                cnt ++;
            } else {
                cnt --;
                if (cnt < 0) {
                    cnt = 0;
                    ans = array[i];
                }
            }
        }
        if (cnt == 0) return 0;
        else return ans;
    }
}
