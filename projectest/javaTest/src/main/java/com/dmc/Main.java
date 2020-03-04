package com.dmc;

import java.util.Scanner;
import java.io.*;
import java.math.*;
import java.util.*;
import java.text.*;
public class Main {

    private static int MAXN;

    static class Matrix {

        long[][] m;
    }

    static final long SMod = 1000000009;

    public static void memset(long value, long[][] t) {
        int l = t.length;
        int r = t[0].length;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < r; j++) {
                t[i][j] = value;
            }
        }
    }

    static Matrix Mul(Matrix a, Matrix b) {
        Matrix c = new Matrix();
        c.m = new long[MAXN][MAXN];
        for (int i = 0; i < MAXN; i++) {
            for (int j = 0; j < MAXN; j++) {
                for (int k = 0; k < MAXN; k++) {
                    c.m[i][j] += (1L * a.m[i][k] * b.m[k][j]) % SMod;
                    c.m[i][j] %= SMod;
                }
            }
        }
        return c;
    }

    static Matrix fastm(Matrix a, long n) {
        Matrix res = new Matrix();
        res.m = new long[MAXN][MAXN];
        memset(0, res.m);
        for (int i = 0; i < MAXN; i++) {
            res.m[i][i] = 1;
        }
        while (n > 0) {
            if ((n & 1) != 0) {
                res = Mul(res, a);
            }
            a = Mul(a, a);
            n >>= 1;
        }
        return res;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 16);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out), 1 << 16);
        //int n = Integer.parseInt(reader.readLine());
        //writer.write(n);
        //主要程序内容写在这里
        long n = Long.parseLong(reader.readLine());
        int total = 0;
        if (n == 1 || n == 2) {
            writer.write(String.valueOf(1));
        } else {
            MAXN = 2;
            Matrix matrix = new Matrix();
            matrix.m = new long[MAXN][MAXN];
            matrix.m[0][0] = 1;
            matrix.m[0][1] = 1;
            matrix.m[1][0] = 1;
            Matrix ans = fastm(matrix, n - 2);
            total += ans.m[0][0];
            total %= SMod;
            total += ans.m[0][1];
            total %= SMod;
            writer.write(String.valueOf(total));
        }

        writer.flush();
    }
}
