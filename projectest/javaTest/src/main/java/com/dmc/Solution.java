package com.dmc;

import com.google.common.collect.Lists;
import com.mchange.v2.lock.SharedUseExclusiveUseLock;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet.B;
import org.apache.hadoop.yarn.webapp.hamlet2.Hamlet.P;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.jetbrains.annotations.NotNull;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 10:05 AM 2020/1/9
 * @Modified By:
 */
public class Solution {

    public void memset(int value, int[][] t, int r) {
        int l = t.length;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < r; j++) {
                t[i][j] = value;
            }
        }
    }

    public void memset(int value, int[] t, int r) {
        for (int i = 0; i < r; i++) {
            t[i] = value;
        }
    }

    public void memset(int value, int[] t) {
        int l = t.length;
        for (int i = 0; i < l; i++) {
            t[i] = value;
        }
    }

    public void memset(long value, long[][] t) {
        int l = t.length;
        int r = t[0].length;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < r; j++) {
                t[i][j] = value;
            }
        }
    }


    public void memset(int value, int[][] t) {
        int l = t.length;
        int r = t[0].length;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < r; j++) {
                t[i][j] = value;
            }
        }
    }


    //========================================自定义排序========================================
    static class Student implements Comparable<Student> {

        public Integer value1;

        public Integer value2;

        public Student(Integer value1, Integer value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        public String toString() {
            return "[" + this.value1 + "," + this.value2 + "]";
        }

        @Override
        public int compareTo(Solution.Student o) {
            if (this.value1.equals(o.value1)) {
                if (this.value2.equals(o.value2)) {
                    return 0;
                }
                return this.value2 - o.value2;
            }
            return this.value1 - o.value1;
        }
    }

    //========================================gcd========================================

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    //========================================树状数组========================================
    private int[] lc;

    private static final int N = 10005;

    private int lowbit(int n) {
        return n & (-n);
    }

    private void add(int n, int i) {
        while (n <= N) {
            lc[n] += i;
            n += lowbit(n);
        }
    }

    //<=n的数量
    private int getSum(int n) {
        int total = 0;
        while (n > 0) {
            total += lc[n];
            n -= lowbit(n);
        }
        return total;
    }

    //========================================dfs========================================

    //========================================前缀树========================================
    static class TrieNode {

        TrieNode[] children;
        String word;

        TrieNode() {
            children = new TrieNode[26];
        }
    }

    public TrieNode init(List<String> roots) {
        TrieNode trie = new TrieNode();
        for (String root : roots) {
            TrieNode cur = trie;
            for (char letter : root.toCharArray()) {
                if (cur.children[letter - 'a'] == null) {
                    cur.children[letter - 'a'] = new TrieNode();
                }
                cur = cur.children[letter - 'a'];
            }
            cur.word = root;
        }
        return trie;
    }

    //========================================bfs========================================

    //========================================并查集======================================
    public int[] p;

    public void initBCJ(int size) {
        p = new int[size];
        for (int i = 0; i < size; i++) {
            p[i] = i;
        }
    }

    public int find(int x)       //查找x元素所在的集合,回溯时压缩路径
    {
        if (x != p[x]) {
            p[x] = find(p[x]);     //回溯时的压缩路径
        }         //从x结点搜索到祖先结点所经过的结点都指向该祖先结点
        return p[x];
    }
    //========================================最短路径======================================

    public Edge[] edges;
    public int cnt;
    public int[] fir;
    public int[] dis;

    static class Edge {

        int u, v, w, next;
        boolean cut;
        int num;
    }

    //距离某点的距离，v为点，w为距离
    static class Node implements Comparable<Node> {

        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(@NotNull Solution.Node o) {
            return this.w - o.w;
        }
    }

    public void dijInit(int edgeSize, int nodeSize) {
        //分配内存，edges，fir，dis
        cnt = 0;
        edges = new Edge[edgeSize + 10];
        fir = new int[edgeSize + 10];
        dis = new int[nodeSize + 10];
        memset(-1, fir);
    }

    //构建邻接表，u代表起点，v代表终点，w代表之间路径
    void addEdge(int u, int v, int w) {
        edges[cnt] = new Edge();
        edges[cnt].v = v;
        edges[cnt].w = w;
        edges[cnt].next = fir[u];
        fir[u] = cnt++;
    }

    public void dijkstra(int s) {
        PriorityQueue<Node> pque = new PriorityQueue<>();
        dis[s] = 0;
        pque.add(new Node(s, 0));
        while (!pque.isEmpty()) {
            Node cur = pque.poll();
            for (int i = fir[cur.v]; i != -1; i = edges[i].next) {
                int v = edges[i].v;
                if (dis[cur.v] + edges[i].w < dis[v]) {
                    dis[v] = dis[cur.v] + edges[i].w;
                    pque.add(new Node(v, dis[v]));
                }
            }
        }
    }

    //========================================图论求桥======================================
//    low数组的下标表示顶点的编号，数组中的值表示DFS中该顶点不通过父顶点能访问到的祖先顶点中最小的顺序值（或者说时间戳）。
    int[] low;
    int[] dfn;
    int recdfn;

    void tarjanAddEdge(int u, int v, int w) {
        edges[cnt] = new Edge();
        edges[cnt].u = u;
        edges[cnt].v = v;
        edges[cnt].w = w;
        edges[cnt].cut = false;
        edges[cnt].num = 0;
        edges[cnt].next = fir[u];
        fir[u] = cnt++;
    }

    public void initTarjan(int nodeSize, int edgeSize) {
        cnt = 0;
        edges = new Edge[edgeSize + 10];
        low = new int[nodeSize + 10];
        dfn = new int[nodeSize + 10];
        fir = new int[edgeSize + 10];
        Arrays.fill(fir, -1);
    }

    public void tarjan(int u, int fa) {
        low[u] = dfn[u] = ++recdfn;
        int have = 0;
        for (int i = fir[u]; i != -1; i = edges[i].next) {
            int v = edges[i].v;
            if (have == 0 && v == fa) {
                //走过你来时的路
                have++;
                continue;
            }
            if (dfn[v] == 0) {
                //dfs过程中还未经过该点
                this.tarjan(v, u);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > dfn[u]) {
                    edges[i].cut = true;
                    edges[i ^ 1].cut = true;
                }
            } else {
                //取已访问的节点的dfs序的最小值
                low[u] = Math.min(low[u], dfn[v]);
            }
        }
    }

    public boolean findEdgeCut(int l, int r) {
        Arrays.fill(low, 0);
        Arrays.fill(dfn, 0);
        recdfn = 0;
        tarjan(l, l);
        for (int i = l; i <= r; i++) {
            if (dfn[i] == 0) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }


    //========================================rmq求区间最值======================================
    public int[][] dp = new int[N][31];

    void rmq_init(int[] arr) {
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            dp[i][0] = arr[i];//初始化
        }
        for (int j = 1; (1 << j) <= l; j++) {
            for (int i = 0; i + (1 << j) - 1 <= l; i++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    int rmq(int l, int r) {
        int k = (int) (Math.log((double) r - l + 1) / Math.log(2.0));
        return Math.max(dp[l][k], dp[r - (1 << k) + 1][k]);
    }

    //=====================================数位dp=========================================
    long dp1[][][][];//dp记忆化数组根据题意编写，确定可保存状态
    int DIG[];

    long dfs(int pos, int cnt1, int cnt2, int cnt3, boolean limit) {
        //printf("%d\n",pos);
        if (pos < 1) {
            // 根据题意编写
            if (cnt1 > 0 && cnt2 > 0 && cnt3 > 0) {
                return 1;
            } else {
                return 0;
            }
        }

        if (!limit && dp1[pos][cnt1][cnt2][cnt3] != -1) {
            return dp1[pos][cnt1][cnt2][cnt3];
        }

        long end1 = limit ? DIG[pos] : 9;
        long ret = 0;
        for (int i = 0; i <= end1; i++) {
            // 根据题意编写
            if (i == 1) {
                ret += dfs(pos - 1, cnt1 + 1, cnt2, cnt3, limit && (i == end1));
            } else if (i == 2) {
                ret += dfs(pos - 1, cnt1, cnt2 + 1, cnt3, limit && (i == end1));
            } else if (i == 3) {
                ret += dfs(pos - 1, cnt1, cnt2, cnt3 + 1, limit && (i == end1));
            } else if (i == 0 && cnt1 == 0 && cnt2 == 0 && cnt3 == 0) {
                ret += dfs(pos - 1, cnt1, cnt2, cnt3, limit && (i == end1));
            }
        }
        if (!limit) {
            dp1[pos][cnt1][cnt2][cnt3] = ret;
        }

        return ret;
    }

    long cal(long x) {
        DIG = new int[15];
        int len = 0;
        while (x > 0) {
            DIG[++len] = (int) (x % 10);
            x = x / 10;
        }
        return dfs(len, 0, 0, 0, Boolean.TRUE);
    }

    //=====================================矩阵快速幂=========================================
    private int MAXN;

    static class Matrix {

        long[][] m;
    }

    static final long SMod = 1000000007;

    Matrix Mul(Matrix a, Matrix b) {
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

    Matrix fastm(Matrix a, long n) {
        Matrix res = new Matrix();
        res.m = new long[MAXN][MAXN];
        memset(0, res.m);
        for (int i = 0; i < MAXN; i++) {
            res.m[i][i] = 1;
        }
        while (n > 0) {
            if ((n & 1) != 0) {
                res = this.Mul(res, a);
            }
            a = Mul(a, a);
            n >>= 1;
        }
        return res;
    }

    //==============================================================================
    long Pow(long a, long n, long p) {
        long x = a;
        long res = 1;
        while (n > 0) {
            if ((n & 1) != 0) {
                res = ((long) res * (long) x) % p;
            }
            n >>= 1;
            x = ((long) x * (long) x) % p;
        }
        return res;
    }

    long Cm(long n, long m, long p) {
        long a = 1, b = 1;
        if (m > n) {
            return 0;
        }
        //实现(a!/(a-b)!) * (b!)^(p-2)) mod p,由于n比较大，所以，此处不知道有什么好的优化
        while (m > 0) {
            a = (a * n) % p;
            b = (b * m) % p;
            m--;
            n--;
        }
        return ((long) a * (long) Pow(b, p - 2, p)) % p;
    }

    long Lucas(long n, long m, long p) {
        if (m == 0) {
            return 1;
        }
        return ((long) Cm(n % p, m % p, p) * (long) Lucas(n / p, m / p, p)) % p;
    }

    long mod = 1000000009;

    long x,y,q;
    //扩展欧几里德
    void ExEuclid(long a, long b) {
        if (b == 0) {
            x = 1;
            y = 0;
            q = a;
            return;
        }
        ExEuclid(b, a % b);
        y -= x * (a / b);
    }

    //乘法逆元
    long inv(long num) {
        ExEuclid(num, mod);
        return (x + mod) % mod;
    }

    long fab[];

    //组合数
    long C(int n, int k) {
        long res = fab[n] * inv(fab[k]);
        res %= mod;
        res *= inv(fab[n - k]);
        res %= mod;
        return res;
    }

    void init() {
        fab[0] = 1;
        for (int i = 1; i <= 1000000; i++) {
            fab[i] = fab[i - 1] * i;
            fab[i] %= mod;
        }
    }

    //=====================================离散=========================================
    static class A {

        public int v;
        public int idx;

        A(int v, int idx) {
            this.v = v;
            this.idx = idx;
        }
    }

    public int[] lisan(int[] arr) {
        int s = arr.length;
        int[] rank = new int[s];
        List<A> l = new ArrayList<>();
        for (int i = 0; i < s; i++) {
            l.add(new A(arr[i], i + 1));
        }
        Collections.sort(l, (a, b) -> {
            return a.v - b.v;
        });
        for (int i = 0; i < s; i++) {
            rank[l.get(i).idx] = i;
        }
        return rank;
    }
    //==============================================================================

    public int lowerBound(int[] arr, int value) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (value >= arr[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    public int lowerBound(long[] arr, long value) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (value >= arr[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l - 1;
    }

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //==============================================================================

    //递减序列，找到一个小于v的下标
    public int lowbound1(int[] arr, int value, int r) {
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (value <= arr[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

//    public int solution(int n, int m, int k, int[][] bad) {
//        MAXN = k;
//        Matrix t = new Matrix();
//        t.m = new long[MAXN][MAXN];
//        memset(1, t.m);
//        for (int i = 0; i < MAXN; i++) {
//            t.m[i][i] = 0;
//        }
//        for (int i = 0; i < m; i++) {
//            t.m[bad[i][1] - 1][bad[i][0] - 1] = 0;
//        }
//        Matrix ans = fastm(t, n);
//        int total = 0;
//        for (int i = 0; i < MAXN; i ++) {
//            for (int j = 0; j < MAXN; j ++) {
//                total += (ans.m[i][j] % SMod);
//                total %= SMod;
//            }
//        }
//        return total;
//    }

    public int solution(int n, int m, int k, int[][] bad) {
        MAXN = k;
        Matrix t = new Matrix();
        t.m = new long[MAXN][MAXN];
        memset(1, t.m);
        for (int i = 0; i < MAXN; i++) {
            t.m[i][i] = 0;
        }
        for (int[] b : bad) {
            t.m[b[1] - 1][b[0] - 1] = 0;
        }
        Matrix ans = fastm(t, n - 1);
        int total = 0;
        for (int i = 0; i < MAXN; i++) {
            for (int j = 0; j < MAXN; j++) {
                total += (ans.m[i][j] % SMod);
                total %= SMod;
            }
        }
        return total;
    }

    public void test(String t, int deepth) {
        char[] cs = t.toCharArray();
        if (deepth == 200) {
            return;
        }
//        System.out.println(t);
        int[] cnt = new int[4];
        String ans = "";
        for (char c : cs) {
            if (c == '1') {
                ans += "3";
                cnt[1]++;
            } else if (c == '2') {
                ans += "13";
                cnt[1]++;
                cnt[3]++;
            } else if (c == '3') {
                ans += "12";
                cnt[1]++;
                cnt[2]++;
            }
        }
        int sum = 0;
        for (int i = 1; i <= 3; i++) {
            sum += cnt[i];
            sum %= SMod;
        }
        System.out.println(sum % SMod);
        test(ans, deepth + 1);
    }

    public void dfsasd() {
        test("123", 1);
    }

    public void dfsGet(int value, int cur, int[] ct, int[] a) {
        if (cur == 0) {
            return;
        }
        int mi = 0x3f3f3f3f;
        int x = a[cur];
        for (int j = 0; j < cur; j++) {
            mi = Math.min(mi, a[j]);
        }
        if (x <= mi) {
            ct[cur] = value / x;
            value -= ct[cur] * x;
            return;
        } else {
            int l = (value - cnt * mi - mi) / (x - mi);
            int r = (value - cnt * mi) / (x - mi);
            for (int i = l; i <= r; i++) {

            }
        }
    }

    //    public int solution(int n, int[] m) {
//        int[] cn = new int[1000025];
//        int z = 0;
//        for (int t : m) {
//            cn[t] ++;
//            z = Math.max(z, t);
//        }
//        boolean flag = false;
//        int zs = 0;
//        int lx = 0;
//        int total = n;
//        for (int i = 0; i < 1000023; i++) {
//            if (cn[i] == 0) {
//                continue;
//            }
//            if (flag) {
//                int now = cn[i];
//                if (now < zs) {
//                    total -= (lx * zs) + zs;
//                    total += zs;
////                    cn[i] += zs;
//                    flag = false;
//                } else {
//                    lx++;
//                }
//            }
//            if (!flag){
//                zs = cn[i] / 2;
//                lx = 1;
//                if (zs > 0) {
//                    flag = true;
//                }
//            }
//        }
//        return total;
//    }
//17711217
    //36650976


    public long solution(int n, String s) {
        String[] zt = new String[100005];
        int z = 0;
        for (int i = 1; i <= 100000; i++) {
            zt[i] = Integer.toBinaryString(i);
            z = Math.max(z, zt[i].length());
        }
        System.out.println(z);
        for (int i = 1; i <= 100; i++) {
//            System.out.println(i - zt[i].length());
            System.out.println(Integer.toBinaryString(i));
        }
        return 1;
    }

    public int solution(int n, int v, int[][] a) {
        int[] left = new int[3005];
        int[] newAdd = new int[3005];
        int[] bj = new int[3005];
        for (int i = 0; i < n; i++) {
            newAdd[a[i][0]] = n * a[i][1];
            bj[a[i][0]] = a[i][1];
        }
        int total = 0;
        for (int i = 1; i < 3004; i++) {
            if (left[i] >= v) {
                total += v;
                left[i + 1] = newAdd[i];
            } else {
                if (newAdd[i] > v - left[i]) {
                    left[i + 1] = newAdd[i] - v + left[i];
                    total += v;
                } else {
                    total += newAdd[i] + left[i];
                }
            }
        }
        return total;
    }

    public void test213() {
        List<Integer> fz = new ArrayList<>();
        List<Integer> fm = new ArrayList<>();
        for (int i = 1; i < 1000; i++) {
            for (int j = 1; j < 1000; j++) {
                if (gcd(i, j) == 1 && i + j < 1000) {
                    fz.add(i);
                    fm.add(j);
                }
            }
        }
//        System.out.println(fz.size());
        int sum = 0;
        for (int i = 0; i < fz.size(); i++) {
            if (fz.get(i) >= 1 && fz.get(i) <= 10 && fm.get(i) >= 1 && fm.get(i) <= 15) {
                sum++;
            }
        }
        System.out.println(sum);
        sum = 0;
        for (int i = 0; i < fz.size(); i++) {
            if (fz.get(i) >= 1 && fz.get(i) <= 4 && fm.get(i) >= 1 && fm.get(i) <= 4) {
                sum++;
            }
        }
        System.out.println(sum);
        sum = 0;
        for (int i = 0; i < fz.size(); i++) {
            if (fz.get(i) >= 5 && fz.get(i) <= 10 && fm.get(i) >= 5 && fm.get(i) <= 15) {
                sum++;
            }
        }
        System.out.println(sum);
        System.out.println("---------------");
    }


    public long solution(long m, long n, long p) {
        long ans = Lucas(m + n - 1, m, p);
        return ans;
    }

    public boolean checkV(String t) {
        char[] cs = t.toCharArray();
        int i = 0;
        for (char c : cs) {
            int l = i - 1;
            int r = i + 1;
            boolean flag = false;
            if (c == 'o') {
                while (l >= 0) {
                    if (cs[l] != 'x') {
                        flag = true;
                        break;
                    }
                    l -= 2;
                }
                while (r < cs.length) {
                    if (cs[r] != 'x') {
                        flag = true;
                        break;
                    }
                    r += 2;
                }
                if (!flag) {
                    return false;
                }
            } else if (c == 'x') {
                while (l >= 0) {
                    if (cs[l] != 'o') {
                        flag = true;
                        break;
                    }
                    l -= 2;
                }
                while (r < cs.length) {
                    if (cs[r] != 'o') {
                        flag = true;
                        break;
                    }
                    r += 2;
                }
                if (!flag) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }
    public void testlist() {
        char[] z = {'o','x','p'};
        int cnt = 0;
        for (int i = 0; i < 3;i ++) {
            for (int j = 0; j < 3;j ++) {
                for (int k = 0; k < 3;k ++) {
                    for (int t = 0; t < 3;t ++) {
                        for (int t1 = 0; t1 < 3;t1 ++) {
                            for (int t2 = 0; t2 < 3;t2 ++) {
                                for (int t3 = 0; t3 < 3;t3 ++) {
                                    for (int t4 = 0; t4 < 3; t4++) {
                                        for (int t5 = 0; t5 < 3;t5 ++) {
                                            for (int t6 = 0; t6 < 3; t6++) {
                                                for (int t7 = 0; t7 < 3;t7 ++) {
                                                    for (int t8 = 0; t8 < 3; t8++) {
                                                        String z1 = "" + z[i] + z[j] + z[k] + z[t] + z[t1] + z[t2] + z[t3] + z[t4] + z[t5] + z[t6] + z[t7] + z[t8];
                                                        if (!checkV(z1)) {
                                                            System.out.println(z1);
                                                            cnt++;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(cnt);
        System.out.println("----------");
    }

    public static void main(String[] args) throws Exception {
        new Solution().testlist();
        new Solution().solution(2, "2134");
        System.out.println(1.0 / 1000000000000L);
        System.out.println(1.0 / 999999999999L);
        new Solution().test213();
//        for (int i = 10000; i <= 10500; i++) {
//            System.out.println(i - zt[i].length());
//        }
//        System.out.println(new Solution().lowerBound(z, 2));
//        Matrix a1 = new Matrix();
//        a1.m = new long[2][2];
//        a1.m[0][0] = 1000000000;
//        a1.m[0][1] = 1000000000;
//        a1.m[1][0] = 1000000000;
//        a1.m[1][1] = 1000000000;
//        Matrix b1 = new Matrix();
//        b1.m = new long[2][2];
//        b1.m[0][0] = 1000000000;
//        b1.m[0][1] = 1000000000;
//        b1.m[1][0] = 1000000000;
//        b1.m[1][1] = 1000000000;
//        Matrix ans = new Solution().Mul(a1, b1);
//        new Solution().dfsasd();
//        int[] t = {2,1,4,5,3};
        File file = new File("/Users/dingmc/Desktop/in.txt");
        FileReader reader = new FileReader(file);
        BufferedReader reader1 = new BufferedReader(reader);
        String t = reader1.readLine().replace("[", "").replace("]", "");
        String[] tt = t.split(",");
//        int[] t2 = new int[tt.length];
        int[][] tt2 = new int[56007][2];
        for (int i = 0; i < tt.length; i += 2) {
            tt2[i / 2][0] = Integer.valueOf(tt[i].trim());
            tt2[i / 2][1] = Integer.valueOf(tt[i + 1].trim());
        }
        int[] t1 = {85, 91, 28, 53, 29, 30, 92, 36, 89};
        int[][] ma = {{4, 1, 5, 3}, {3, 2, 7, 7}, {6, 5, 2, 8}, {8, 9, 4, 5}};
        int[][] z4 = {{98, 7}, {53, 54}, {29, 56}, {45, 26}, {12, 45}, {17, 96}, {93, 77}, {65, 43}, {35, 4}, {43, 40}};
        int[] left = {1, -1, -1, 4, -1, -1};
        int[] right = {2, -1, -1, 5, -1, -1};
        System.out.println(new Solution().solution(1000000000000L, 667, 97843));
        System.out.println("---------");
        int a = 2147483647;
        int b = 2147483647;
        System.out.println((123493803l * 123494892) % 1000000007);
        int[][] mat = {{2, 1}, {3, 1}, {4, 2}, {1, 4}};
        int[] ls = {-2147483648, -2147483648, 2147483647, -2147483648, 1, 3, -2147483648, -100, 8, 17, 22, -2147483648, -2147483648, 2147483647, 2147483647,
            2147483647, 2147483647, -2147483648, 2147483647, -2147483648};
        int[] values = {9, 8, 8, 7, 6};
        int[] labels = {0, 0, 0, 1, 1};
        int num_wanted = 3;
        int use_limit = 2;
        int K = 1;
//        System.out.println((long)100000000 * 100000000);
        int[] arr = {-1, -2};
        List<List<Integer>> conns = new ArrayList<>();
        conns.add(Lists.newArrayList(0, 1));
        conns.add(Lists.newArrayList(1, 2));
        conns.add(Lists.newArrayList(2, 0));
        conns.add(Lists.newArrayList(1, 3));

//        System.out.println(String.format("%05d", 123));
        int[] zz = {2, 2, 1, 1, 1};
        long start = System.currentTimeMillis();
//        System.out.println(new Solution().licenseKeyFormatting("sd-adf-asdfa-asf", 6));
//        System.out.println(new Solution().largestMultipleOfThree(zz));
//        List<List<Integer>> t = new Solution().findBestValue(ls,);
        long end = System.currentTimeMillis();
        System.out.println(start - end);
//        System.out.println(t);
    }
}
