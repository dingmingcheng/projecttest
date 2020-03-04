package com.dmc;

/**
 * @Author:dingmc
 * @Description:
 * @Date: Created in 2:47 PM 2019/3/12
 * @Modified By:
 */
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.hadoop.yarn.webapp.Params;

public class HttpUtilTest {

    public void memset(int value, int[][] t, int r) {
        int l = t.length;
        for (int i = 0; i < l; i ++) {
            for (int j = 0; j < r; j ++) {
                t[i][j] = value;
            }
        }
    }

    public void memset(int value, int[] t, int r) {
        for (int i = 0; i < r; i ++) {
            t[i] = value;
        }
    }

    private int[] lc;




    //    G = 5, P = 3, group = [2,2], profit = [2,3]
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int[][][] dp = new int[2][102][10202];
        int mod = 1000000007;
        int gl = group.length;
        int sum2 = 5;
        for (int pro : profit) {
            sum2 += pro;
        }
        int max1 = 103;
        for (int i = 0; i < gl; i++) {
            int last = 1 - (i % 2);
            int now = i % 2;
            dp[last][0][0] = 1;
//            System.out.println("now:" + now);
//            for (int j = 0; j < 7; j++) {
//                for (int k = 0; k < 7; k++) {
//                    System.out.print(dp[last][j][k]);
//                }
//                System.out.println();
//            }
//            System.out.println("=============");
            int right = Math.min(sum2, (i + 1) * 100 + 5);
//            memset(0, dp[now], right);
            for (int j = 0; j <= G; j++) {
                for (int k = 0; k < right; k++) {
                    dp[now][j][k] = dp[last][j][k];
                    if (j < group[i] || k < profit[i]) {
                        continue;
                    }
                    dp[now][j][k] += dp[last][j - group[i]][k - profit[i]];
                    dp[now][j][k] = dp[now][j][k] % mod;
                }
            }
        }
        int sum = 0;
        int t = (gl - 1) % 2;
//        for (int j = 0; j < 9; j++) {
//            for (int k = 0; k < 9; k++) {
//                System.out.print(dp[1- t][j][k]);
//            }
//            System.out.println();
//        }
//        System.out.println("=============");
        for (int i = P; i < 10002; i++) {
            for (int j = 0; j <= G; j++) {
                sum += dp[t][j][i];
                sum %= mod;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
//        int[][][] dp = new int[2][101][10002];
//        memset(3, dp[0]);
//        System.out.println(dp[0][0][1]);
//        G = 10, P = 5, group = [2,3,5], profit = [6,7,8]
        HttpUtilTest test = new HttpUtilTest();
        int[] group = {38,43,33,42,44};
        int[] profit = {61,44,38,42,41};
        System.out.println(test.profitableSchemes(100, 100, group, profit));
    }

//    100
//        100
//        [38,43,33,42,44]
//        [61,44,38,42,41]
//    100
//        10
//        [66,24,53,49,86,37,4,70,99,68,14,91,70,71,70,98,48,26,13,86,4,82,1,7,51,37,27,87,2,65,93,66,99,28,17,93,83,91,45,3,59,87,92,62,77,21,9,37,11,4,69,46,70,47,28,40,74,47,12,3,85,16,91,100,39,24,52,50,40,23,64,22,2,15,18,62,26,76,3,60,64,34,45,40,49,11,5,8,40,71,12,60,3,51,31,5,42,52,15,36]
//        [8,4,8,8,9,3,1,6,7,10,1,10,4,9,7,11,5,1,7,4,11,1,5,9,9,5,1,10,0,10,4,1,1,1,6,9,3,6,2,5,4,7,8,5,2,3,0,6,4,5,9,9,10,7,1,8,9,6,0,2,9,2,2,8,6,10,3,4,6,1,10,7,5,4,8,1,8,5,5,4,1,1,10,0,8,0,1,11,5,4,7,9,1,11,1,0,1,6,8,3]
    /**
     * @作用 使用urlconnection
     * @param url
     * @param Params
     * @return
     * @throws IOException
     */
    public static String sendPost(String url,String Params)throws IOException{
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        String response="";
        try {
            URL httpUrl = null; //HTTP URL类 用这个类来创建连接
            //创建URL
            httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setRequestProperty("Cookie", "XXL_JOB_LOGIN_IDENTITY=3366363763626434616436316339626664636630396131366230666134336630");
            conn.setUseCaches(false);//设置不要缓存
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //POST请求
            out = new OutputStreamWriter(
                conn.getOutputStream());
            out.write(Params);
            out.flush();
            //读取响应
            reader = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                response+=lines;
            }
            reader.close();
            // 断开连接
            conn.disconnect();

            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(reader!=null){
                    reader.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }

        return response;
    }
}
