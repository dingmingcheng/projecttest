package com.dmc;

public class HttpResult {

    // 响应码
    private Integer code;

    // 响应体
    private String body;

    public HttpResult() {
        super();
    }

    public HttpResult(Integer code, String body) {
        super();
        this.code = code;
        this.body = body;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static void main(String[] args) {
        int[] w = { 0, 2, 3, 4, 5, 9, 12, 2};
        int[] v = { 0, 3, 4, 5, 8, 10, 1, 18};
        int N = w.length, W = 21;
        int[][] b = new int[N][W];
        int[][] flag = new int[N][W];
        for (int k = 1; k < N; k++) {
            for (int c = 1; c < W; c++) {
                if (w[k] > c) {
                    b[k][c] = b[k - 1][c];
                } else {
                    int value1 = b[k - 1][c - w[k]] + v[k]; // 拿第k件物品
                    int value2 = b[k - 1][c]; // 不拿第k件物品
                    b[k][c] = Math.max(value1, value2);
                    flag[k][c] = value1 > value2 ? c - w[k] : 0;
                }
            }
        }
        System.out.println(b[N - 1][W - 1]);

//        for (int j =0;j<W;j++){
//            System.out.printf("%3d", j);
//        }
//        System.out.println();
//        for(int i=0;i<N;i++){
//            for (int j =0;j<W;j++){
//                System.out.printf("%3d", b[i][j]);
//            }
//            System.out.println();
//        }

        int i = N - 1;
        int value = flag[i][W - 1];
        while (value == 0) {
            i --;
            value = flag[i][W - 1];
        }
        System.out.println("index :" + i);
        while (value != 0) {
            int z = flag[i - 1][value];
            if ((value - z) == w[i - 1]) {
                System.out.println("index :" + (i - 1));
                value = z;
            }
            i = i - 1;
        }
    }
}
