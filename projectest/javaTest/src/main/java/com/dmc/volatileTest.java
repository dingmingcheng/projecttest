package com.dmc;

/**
 * @Author: dingmingcheng
 * @Email: mingcheng.ding@qianli-inc.com
 * @Description
 * @Date: Created in 下午3:45 2018/2/14
 * @Modifyed By:
 */
public class volatileTest {

    static int a = 0, a1 = 0;
    static int b = 0, b1 = 0;

    static class TaskA implements Runnable {

        @Override
        public void run() {
            a = 1;
            a1 = b;
        }
    }
    static class TaskB implements Runnable {

        @Override
        public void run() {
            b = 1;
            b1 = a;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int time = 0;
        while (true) {
            Thread aTask = new Thread(new TaskA());
            Thread bTask = new Thread(new TaskB());
            aTask.start();
            bTask.start();
            aTask.join();
            bTask.join();
            System.out.println(time++ + ": a1 = " + a1 + "and b1 = " + b1);
            if (a1 == 0 && b1 == 0) {
                break;
            }
            a = 0;
            a1 = 0;
            b = 0;
            b1 = 0;
        }
    }
}
