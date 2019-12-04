package com.dmc;

/**
 * 统计一个数字在排序数组中出现的次数
 */
public class problem3 {
    public static void main(String[] args) {
        int[] array = new int[]{3};
        int k = 3;
        int cnt = GetNumberOfK(array ,k);
        System.out.println(cnt);
    }

    public static int GetNumberOfK(int [] array , int k) {
        int left = 0;
        int right = array.length - 1;
        int ans = 0;
        int flag = 0;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (array[mid] < k) {
                left = mid + 1;
            } else if (array[mid] > k) {
                right = mid - 1;
            } else {
                ans = mid;
                flag = 1;
                break;
            }
        }
        if (flag == 0) return 0;
        int index = ans - 1;
        int cnt = 1;
        while (index >= 0 && array[index --] == k) {
            cnt ++;
        }
        index = ans + 1;
        while (index < array.length && array[index ++] == k) {
            cnt ++;
        }
        return cnt;
    }
}
