package com.dmc;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 1:58 PM 2019/9/29
 * @Modified By:
 */
public class algorithm {

    public static void main(String[] args) {
        int[] a = new int[]{12, 13, 54, 17, 60};
        int n = a.length;
        int[][] dp = new int[n][getSize(n)];

//        System.out.println((int) Math.ceil(Math.log((double) n)));
//        System.out.println(Math.log((double) n));
        for (int i = 0; i < n; i++) {
            dp[i][0] = a[i];
        }
        for (int j = 1; (1 << j) <= n; j++) {
            for (int i = 0; i + (1 << (j - 1)) < n; i ++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + (int) Math.pow((double) 2, j - 1)][j - 1]);
            }
        }

        int l = 2;
        int r = 5;
        l = l - 1;
        r = r - 1;
        int k = (int) Math.log((double)(r - l + 1));
        int ans = Math.max(dp[l][k], dp[r - (1 << k) + 1][k]);
        System.out.println(ans);
    }

    public static int getSize(int z) {
        int ans = 0;
        while (z > 0) {
            z /= 2;
            ans ++;
        }
        return ans;
    }
}
