package com.dmc;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 */
public class problem6 {
    public static final int MOD = 1000000007;

    public static int[] a = new int[100020];
    public static int lowbit(int x) {
        return x & (-x);
    }
    public static void modify(int x,int add)
    {
        while(x<=100005)//!!!!!!!!!!!!!!MAXN
        {
            a[x]+=add;
            x+=lowbit(x);
        }
    }
    public static int get_sum(int x)//<=x
    {
        int ret=0;
        while(x!=0)
        {
            ret+=a[x];
            x-=lowbit(x);
        }
        return ret;
    }
    static class a {
        public Integer num;
        public Integer index;

        public a(int num, int index) {
            this.num = num;
            this.index = index;
        }
    }
    public static void main(String[] args) {
        int[] array = new int[]{5,65,6,32};
        int t = InversePairs(array);
        System.out.println(t);
    }
    public static int InversePairs(int [] array) {
        for (int i = 0; i <= 100005; i ++) {
            a[i] = 0;
        }
        System.out.println(a[1]);
        int length = array.length;
        a[] arrays = new a[array.length];
        int[] rank = new int[array.length];
        for(int i = 0; i < array.length; ++i) {
            arrays[i] = new a(array[i], i);
        }
        Arrays.sort(arrays, new Comparator<problem6.a>() {
            @Override
            public int compare(problem6.a o1, problem6.a o2) {
                return o1.num.compareTo(o2.num);
            }
        });
        for(int i = 0; i < array.length; ++i) rank[arrays[i].index] = i + 1;

        long answer = 0;
        for (int i = 0; i < length; i ++) {
            int res = get_sum(rank[i] - 1);
            answer += (i - res);
            answer %= MOD;
            modify(rank[i], 1);
        }
        int ans = (int) answer;
        return ans;
    }
}
/*
class Solution {
public:
    const int MOD = 1000000007;
    int a[200020];
    int lowbit(int x) {
        return x & (-x);
    }
    void modify(int x,int add)
    {
        while(x<=200005)//!!!!!!!!!!!!!!MAXN
        {
            a[x]+=add;
            x+=lowbit(x);
        }
    }
    int get_sum(int x)//<=x
    {
        int ret=0;
        while(x!=0)
        {
            ret+=a[x];
            x-=lowbit(x);
        }
        return ret;
    }
    struct at {
        int num;
        int index;
        bool operator < (const at &rhs) const {
            return num < rhs.num;
        }
    };
    int InversePairs(vector<int> data) {
        for (int i = 0; i <= 200005; i ++) {
            a[i] = 0;
        }
        int length = data.size();
        at arrays[length];
        int rank[length];
        for(int i = 0; i < length; ++i) {
            arrays[i].num = data[i];
            arrays[i].index = i;
        }
        sort(arrays, arrays + length);
        for(int i = 0; i < length; ++i) rank[arrays[i].index] = i + 1;

        long answer = 0;
        for (int i = 0; i < length; i ++) {
            int res = get_sum(rank[i] - 1);
            answer += (i - res);
            answer %= MOD;
            modify(rank[i], 1);
        }
        int ans = (int) answer;
        return ans;
    }
};
 */